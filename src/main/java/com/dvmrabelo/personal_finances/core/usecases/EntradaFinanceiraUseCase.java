package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.CustomUserDetails;
import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.entity.EntradaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.repository.EntradaFinanceiraRepository;
import com.dvmrabelo.personal_finances.dataprovider.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaFinanceiraUseCase {

    @Autowired
    private EntradaFinanceiraRepository entradaFinanceiraRepository;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    public List<EntradaFinanceira> findAll() {
        Long userId = getUserId();

        return entradaFinanceiraRepository.findAllByUserId(userId);
    }

    @PreAuthorize("hasRole('USER')")
    public Optional<EntradaFinanceira> findById(Long id) {
        Long userId = getUserId();

        return entradaFinanceiraRepository.findByUserIdAndId(userId, id);
    }

    @PreAuthorize("hasRole('USER')")
    public EntradaFinanceira createEntradaFinanceira(EntradaFinanceira entradaFinanceira) {
        return entradaFinanceiraRepository.save(entradaFinanceira);
    }

    @PreAuthorize("hasRole('USER') and @entradaFinanceiraUseCase.isUserEntradaFinanceiraOwner(#id)")
    public Optional<EntradaFinanceira> updateEntradaFinanceira(Long id, EntradaFinanceira newEntradaFinanceira) {
        Optional<EntradaFinanceira> entradaFinanceiraOpt = entradaFinanceiraRepository.findById(id);
        if(entradaFinanceiraOpt.isPresent()) {
            EntradaFinanceira entradaFinanceira = entradaFinanceiraOpt.get();
            entradaFinanceira.setDados(newEntradaFinanceira);
            return Optional.of(entradaFinanceiraRepository.save(entradaFinanceira));
        } else {
            return Optional.empty();
        }
    }

    @PreAuthorize("hasRole('USER') and @entradaFinanceiraUseCase.isUserEntradaFinanceiraOwner(#id)")
    public void deleteById(Long id) {
        entradaFinanceiraRepository.deleteById(id);
    }

    public boolean isUserEntradaFinanceiraOwner(Long entradaId) {
        Long userId = getUserId();

        Optional<EntradaFinanceira> documentOpt = entradaFinanceiraRepository.findById(entradaId);
        return documentOpt.map(document -> document.getCreatedBy().equals(userId)).orElse(false);
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
