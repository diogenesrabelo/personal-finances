package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.CustomUserDetails;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.SaidaFinanceiraTipo;
import com.dvmrabelo.personal_finances.dataprovider.tiposaida.repository.SaidaFinanceiraTipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaidaFinanceiraTipoUseCase {

    @Autowired
    private SaidaFinanceiraTipoRepository saidaFinanceiraTipoRepository;

    @PreAuthorize("hasRole('USER')")
    public List<SaidaFinanceiraTipo> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return saidaFinanceiraTipoRepository.findAllByUserId(userId);
    }

    @PreAuthorize("hasRole('USER')")
    public Optional<SaidaFinanceiraTipo> findById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return saidaFinanceiraTipoRepository.findByUserIdAndId(userId, id);
    }

    @PreAuthorize("hasRole('USER')")
    public SaidaFinanceiraTipo createSaidaFinanceiraTipo(SaidaFinanceiraTipo saidaFinanceiraTipo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return saidaFinanceiraTipoRepository.save(saidaFinanceiraTipo);
    }

    @PreAuthorize("hasRole('USER') and @saidaFinanceiraTipoUseCase.isUserSaidaFinanceiraTipoOwner(#saidaTipoId)")
    public void deactivate(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        saidaFinanceiraTipoRepository.findById(id).ifPresent(tipo -> {
            SaidaFinanceiraTipo desativado = new SaidaFinanceiraTipo(
                    tipo.id(),
                    tipo.nome(),
                    tipo.descricao(),
                    false
            );
            saidaFinanceiraTipoRepository.save(desativado);
        });
    }

    @PreAuthorize("hasRole('USER') and @saidaFinanceiraTipoUseCase.isUserSaidaFinanceiraTipoOwner(#saidaTipoId)")
    public Optional<SaidaFinanceiraTipo> updateSaidaFinanceiraTipo(Long saidaTipoId, SaidaFinanceiraTipo newSaidaFinanceiraTipo) {
        Optional<SaidaFinanceiraTipo> saidaFinanceiraTipoOpt = saidaFinanceiraTipoRepository.findById(saidaTipoId);
        if(saidaFinanceiraTipoOpt.isPresent()) {
            SaidaFinanceiraTipo saidaFinanceiraTipo = saidaFinanceiraTipoOpt.get();
            saidaFinanceiraTipo.setDados(newSaidaFinanceiraTipo);
            return Optional.of(saidaFinanceiraTipoRepository.save(saidaFinanceiraTipo));
        } else {
            return Optional.empty();
        }
    }

    public boolean isUserSaidaFinanceiraTipoOwner(Long saidaTipoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        Optional<SaidaFinanceiraTipo> documentOpt = saidaFinanceiraTipoRepository.findById(saidaTipoId);
        return documentOpt.map(document -> document.getCreatedBy().equals(userId)).orElse(false);
    }
}
