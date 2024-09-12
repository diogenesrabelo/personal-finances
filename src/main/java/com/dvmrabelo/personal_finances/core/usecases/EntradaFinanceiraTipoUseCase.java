package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.CustomUserDetails;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.EntradaFinanceiraTipo;
import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.repository.EntradaFinanceiraTipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaFinanceiraTipoUseCase {

    @Autowired
    private EntradaFinanceiraTipoRepository entradaFinanceiraTipoRepository;

    @PreAuthorize("hasRole('USER')")
    public List<EntradaFinanceiraTipo> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return entradaFinanceiraTipoRepository.findAllByUserId(userId);
    }

    @PreAuthorize("hasRole('USER')")
    public Optional<EntradaFinanceiraTipo> findById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return entradaFinanceiraTipoRepository.findByUserIdAndId(userId, id);
    }

    @PreAuthorize("hasRole('USER')")
    public EntradaFinanceiraTipo createEntradaFinanceiraTipo(EntradaFinanceiraTipo entradaFinanceiraTipo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return entradaFinanceiraTipoRepository.save(entradaFinanceiraTipo);
    }

    @PreAuthorize("hasRole('USER') and @entradaFinanceiraTipoUseCase.isUserEntradaFinanceiraTipoOwner(#entradaTipoId)")
    public void deactivate(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        entradaFinanceiraTipoRepository.findById(id).ifPresent(tipo -> {
            EntradaFinanceiraTipo desativado = new EntradaFinanceiraTipo(
                    tipo.id(),
                    tipo.nome(),
                    tipo.descricao(),
                    false
            );
            entradaFinanceiraTipoRepository.save(desativado);
        });
    }

    public boolean isUserEntradaFinanceiraTipoOwner(Long entradaTipoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        Optional<EntradaFinanceiraTipo> documentOpt = entradaFinanceiraTipoRepository.findById(entradaTipoId);
        return documentOpt.map(document -> document.getCreatedBy().equals(userId)).orElse(false);
    }

    public Optional<EntradaFinanceiraTipo> updateEntradaFinanceiraTipo(Long id, EntradaFinanceiraTipo entradaFinanceiraTipo) {
        Optional<EntradaFinanceiraTipo> entradaFinanceiraTipoOpt = entradaFinanceiraTipoRepository.findById(id);
        if(entradaFinanceiraTipoOpt.isPresent()) {
            EntradaFinanceiraTipo entradaFinanceiraTipoFound = entradaFinanceiraTipoOpt.get();
            entradaFinanceiraTipoFound.setDados(entradaFinanceiraTipo);
            return Optional.of(entradaFinanceiraTipoRepository.save(entradaFinanceiraTipoFound));
        } else {
            return Optional.empty();
        }
    }
}
