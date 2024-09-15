package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.CustomUserDetails;
import com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.entity.SaidaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.repository.SaidaFinanceiraRepository;
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
public class SaidaFinanceiraUseCase {

    @Autowired
    private SaidaFinanceiraRepository saidaFinanceiraRepository;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    public List<SaidaFinanceira> findAll() {
        Long userId = getUserId();
        return saidaFinanceiraRepository.findAllByUserId(userId);
    }

    @PreAuthorize("hasRole('USER')")
    public Optional<SaidaFinanceira> findById(Long id) {
        Long userId = getUserId();
        return saidaFinanceiraRepository.findByUserIdAndId(userId, id);
    }

    @PreAuthorize("hasRole('USER')")
    public SaidaFinanceira createSaidaFinanceira(SaidaFinanceira saidaFinanceira) {
        return saidaFinanceiraRepository.save(saidaFinanceira);
    }

    @PreAuthorize("hasRole('USER') and @saidaFinanceiraUseCase.isUserSaidaFinanceiraOwner(#saidaId)")
    public Optional<SaidaFinanceira> updateSaidaFinanceira(Long saidaId, SaidaFinanceira newSaidaFinanceira) {
         Optional<SaidaFinanceira> saidaFinanceiraOpt = saidaFinanceiraRepository.findById(saidaId);
         if(saidaFinanceiraOpt.isPresent()) {
             SaidaFinanceira saidaFinanceira = saidaFinanceiraOpt.get();
             saidaFinanceira.setDados(newSaidaFinanceira);
             return Optional.of(saidaFinanceiraRepository.save(saidaFinanceira));
         } else {
             return Optional.empty();
         }
    }

    @PreAuthorize("hasRole('USER') and @saidaFinanceiraUseCase.isUserSaidaFinanceiraOwner(#saidaId)")
    public void deleteById(Long saidaId) {
        saidaFinanceiraRepository.deleteById(saidaId);
    }

    public boolean isUserSaidaFinanceiraOwner(Long documentId) {
        Long userId = getUserId();

        Optional<SaidaFinanceira> documentOpt = saidaFinanceiraRepository.findById(documentId);
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
