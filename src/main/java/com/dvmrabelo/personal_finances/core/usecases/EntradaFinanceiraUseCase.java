package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.CustomUserDetails;
import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.entity.EntradaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.repository.EntradaFinanceiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaFinanceiraUseCase {

    @Autowired
    private EntradaFinanceiraRepository entradaFinanceiraRepository;

    @PreAuthorize("hasRole('USER')")
    public List<EntradaFinanceira> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return entradaFinanceiraRepository.findAllByUserId(userId);
    }

    @PreAuthorize("hasRole('USER')")
    public Optional<EntradaFinanceira> findById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return entradaFinanceiraRepository.findByUserIdAndId(userId, id);
    }

    @PreAuthorize("hasRole('USER')")
    public EntradaFinanceira createEntradaFinanceira(EntradaFinanceira entradaFinanceira) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        Optional<EntradaFinanceira> documentOpt = entradaFinanceiraRepository.findById(entradaId);
        return documentOpt.map(document -> document.getCreatedBy().equals(userId)).orElse(false);
    }
}
