package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.input.TipoSaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoSaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.core.gateways.TipoSaidaFinanceiraGateway;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import com.dvmrabelo.personal_finances.dataprovider.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaidaFinanceiraTipoUseCase {

    @Autowired
    private TipoSaidaFinanceiraGateway tipoSaidaFinanceiraGateway;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    public List<TipoSaidaFinanceiraOutput> findAll() {
        Long userId = getUser().getId();

        return tipoSaidaFinanceiraGateway.findAllByUserId(userId);
    }

    @PreAuthorize("hasRole('USER')")
    public TipoSaidaFinanceiraOutput findById(Long id) {
        Long userId = getUser().getId();

        return tipoSaidaFinanceiraGateway.findByUserIdAndId(userId, id);
    }

    @PreAuthorize("hasRole('USER')")
    public TipoSaidaFinanceiraOutput createSaidaFinanceiraTipo(TipoSaidaFinanceiraInput tipoSaidaFinanceira) {
        UserEntity user = getUser();

        return tipoSaidaFinanceiraGateway.save(tipoSaidaFinanceira, user);
    }

    @PreAuthorize("hasRole('USER') and @saidaFinanceiraTipoUseCase.isUserSaidaFinanceiraTipoOwner(#saidaTipoId)")
    public void deactivate(Long id) {
        tipoSaidaFinanceiraGateway.deactivateTipoSaida(id);
    }

    @PreAuthorize("hasRole('USER') and @saidaFinanceiraTipoUseCase.isUserSaidaFinanceiraTipoOwner(#saidaTipoId)")
    public TipoSaidaFinanceiraOutput updateSaidaFinanceiraTipo(Long saidaTipoId, TipoSaidaFinanceiraInput newTipoSaidaFinanceira) {TipoSaidaFinanceiraOutput saidaFinanceiraTipo = tipoSaidaFinanceiraGateway.findById(saidaTipoId);

        return tipoSaidaFinanceiraGateway.update(newTipoSaidaFinanceira, saidaTipoId);

    }

    public boolean isUserSaidaFinanceiraTipoOwner(Long saidaTipoId) {
        Long userId = getUser().getId();

        TipoSaidaFinanceiraOutput document = tipoSaidaFinanceiraGateway.findById(saidaTipoId);
        return document.createdBy().getId().equals(userId);
    }

    private UserEntity getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name;
        if (principal instanceof UserDetails) {
            name = ((UserDetails)principal).getUsername();
            var user = userRepository.findByUsername(name);
            if (user.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado.");
            }
            return user.get();
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }
}
