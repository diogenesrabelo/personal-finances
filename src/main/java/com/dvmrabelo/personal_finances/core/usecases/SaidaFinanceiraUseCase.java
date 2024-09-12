package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.CustomUserDetails;
import com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.entity.SaidaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.repository.SaidaFinanceiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaidaFinanceiraUseCase {

    @Autowired
    private SaidaFinanceiraRepository saidaFinanceiraRepository;

    @PreAuthorize("hasRole('USER')")
    public List<SaidaFinanceira> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        return saidaFinanceiraRepository.findAllByUserId(userId);
    }

    @PreAuthorize("hasRole('USER')")
    public Optional<SaidaFinanceira> findById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        return saidaFinanceiraRepository.findByUserIdAndId(userId, id);
    }

    @PreAuthorize("hasRole('USER')")
    public SaidaFinanceira createSaidaFinanceira(SaidaFinanceira saidaFinanceira) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        Optional<SaidaFinanceira> documentOpt = saidaFinanceiraRepository.findById(documentId);
        return documentOpt.map(document -> document.getCreatedBy().equals(userId)).orElse(false);
    }
}
