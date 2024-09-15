package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.input.TipoEntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.TipoEntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.core.gateways.EntradaFinanceiraGateway;
import com.dvmrabelo.personal_finances.core.gateways.TipoEntradaFinanceiraGateway;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.TipoEntradaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.repository.TipoEntradaFinanceiraRepository;
import com.dvmrabelo.personal_finances.dataprovider.user.repository.UserRepository;
import com.dvmrabelo.personal_finances.entrypoint.api.tipoentradafinanceira.dto.TipoEntradaFinanceiraOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaFinanceiraTipoUseCase {

    @Autowired
    private TipoEntradaFinanceiraGateway tipoEntradaFinanceiraGateway;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    public List<TipoEntradaFinanceiraOutput> findAll() {
        Long userId = getUserId();

        return tipoEntradaFinanceiraGateway.findAll(userId);
    }

    @PreAuthorize("hasRole('USER')")
    public TipoEntradaFinanceiraOutput findById(Long id) {
        Long userId = getUserId();

        return tipoEntradaFinanceiraGateway.findById(userId, id);
    }

    @PreAuthorize("hasRole('USER')")
    public TipoEntradaFinanceiraOutput createEntradaFinanceiraTipo(TipoEntradaFinanceiraInput tipoEntradaFinanceira) {
        Long userId = getUserId();

        return tipoEntradaFinanceiraGateway.save(tipoEntradaFinanceira);
    }

    @PreAuthorize("hasRole('USER') and @entradaFinanceiraTipoUseCase.isUserEntradaFinanceiraTipoOwner(#entradaTipoId)")
    public void deactivate(Long id) {
        tipoEntradaFinanceiraGateway.deactivateTipoEntrada(id);
    }

    public boolean isUserEntradaFinanceiraTipoOwner(Long entradaTipoId) {
        Long userId = getUserId();

        TipoEntradaFinanceiraOutput document = tipoEntradaFinanceiraGateway.findById(entradaTipoId);
        return document.createdBy().getId().equals(userId);
    }

    public TipoEntradaFinanceiraOutput updateEntradaFinanceiraTipo(Long id, TipoEntradaFinanceira tipoEntradaFinanceira) {
        return tipoEntradaFinanceiraGateway.findById(id);
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
