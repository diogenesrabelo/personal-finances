package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.input.EntradaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.EntradaFinanceiraOutput;
import com.dvmrabelo.personal_finances.core.gateways.EntradaFinanceiraGateway;
import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.entity.EntradaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import com.dvmrabelo.personal_finances.dataprovider.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaFinanceiraUseCase {

    @Autowired
    private EntradaFinanceiraGateway entradaFinanceiraGateway;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    public List<EntradaFinanceiraOutput> findAll() {
        Long userId = getUser().getId();

        return entradaFinanceiraGateway.findAllByUserId(userId);
    }

    @PreAuthorize("hasRole('USER')")
    public EntradaFinanceiraOutput findById(Long id) {
        Long userId = getUser().getId();

        return entradaFinanceiraGateway.findByUserIdAndId(userId, id);
    }

    @PreAuthorize("hasRole('USER')")
    public EntradaFinanceiraOutput createEntradaFinanceira(EntradaFinanceiraInput entradaFinanceira) {
        UserEntity user = getUser();
        return entradaFinanceiraGateway.save(entradaFinanceira, user);
    }

    @PreAuthorize("hasRole('USER') and @entradaFinanceiraUseCase.isUserEntradaFinanceiraOwner(#id)")
    public EntradaFinanceiraOutput updateEntradaFinanceira(Long id, EntradaFinanceiraInput newEntradaFinanceira) {
        return entradaFinanceiraGateway.update(newEntradaFinanceira, id);
    }

    @PreAuthorize("hasRole('USER') and @entradaFinanceiraUseCase.isUserEntradaFinanceiraOwner(#id)")
    public void deleteById(Long id) {
        entradaFinanceiraGateway.delete(id);
    }

    public boolean isUserEntradaFinanceiraOwner(Long entradaId) {
        Long userId = getUser().getId();

        EntradaFinanceiraOutput entrada = entradaFinanceiraGateway.findById(entradaId);
        return entrada.createdBy().getId().equals(userId);
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
