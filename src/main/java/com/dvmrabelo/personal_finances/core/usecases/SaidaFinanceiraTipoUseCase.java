package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.input.TipoSaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoSaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.core.gateways.TipoSaidaFinanceiraGateway;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.TipoSaidaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.repository.TipoSaidaFinanceiraRepository;
import com.dvmrabelo.personal_finances.dataprovider.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaidaFinanceiraTipoUseCase {

    @Autowired
    private TipoSaidaFinanceiraGateway tipoSaidaFinanceiraGateway;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    public List<TipoSaidaFinanceiraOutput> findAll() {
        Long userId = getUserId();

        return tipoSaidaFinanceiraGateway.findAllByUserId(userId);
    }

    @PreAuthorize("hasRole('USER')")
    public TipoSaidaFinanceiraOutput findById(Long id) {
        Long userId = getUserId();

        return tipoSaidaFinanceiraGateway.findByUserIdAndId(userId, id);
    }

    @PreAuthorize("hasRole('USER')")
    public TipoSaidaFinanceiraOutput createSaidaFinanceiraTipo(TipoSaidaFinanceiraInput tipoSaidaFinanceira) {
        Long userId = getUserId();

        return tipoSaidaFinanceiraGateway.save(tipoSaidaFinanceira);
    }

    @PreAuthorize("hasRole('USER') and @saidaFinanceiraTipoUseCase.isUserSaidaFinanceiraTipoOwner(#saidaTipoId)")
    public void deactivate(Long id) {
        Long userId = getUserId();
        tipoSaidaFinanceiraGateway.deactivateTipoSaida(id);
    }

    @PreAuthorize("hasRole('USER') and @saidaFinanceiraTipoUseCase.isUserSaidaFinanceiraTipoOwner(#saidaTipoId)")
    public TipoSaidaFinanceiraOutput updateSaidaFinanceiraTipo(Long saidaTipoId, TipoSaidaFinanceiraInput newTipoSaidaFinanceira) {TipoSaidaFinanceiraOutput saidaFinanceiraTipo = tipoSaidaFinanceiraGateway.findById(saidaTipoId);

        return tipoSaidaFinanceiraGateway.update(newTipoSaidaFinanceira);

    }

    public boolean isUserSaidaFinanceiraTipoOwner(Long saidaTipoId) {
        Long userId = getUserId();

        TipoSaidaFinanceiraOutput document = tipoSaidaFinanceiraGateway.findById(saidaTipoId);
        return document.createdBy().getId().equals(userId);
    }

    private Long getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name;
        if (principal instanceof UserDetails) {
            name = ((UserDetails)principal).getUsername();
            var user = userRepository.findByUsername(name);
            if (user.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado.");
            }
            return user.get().getId();
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }
}
