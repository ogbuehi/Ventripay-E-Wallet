package com.example.Testing_E_Wallet.repository;

import com.example.Testing_E_Wallet.model.Role;
import com.example.Testing_E_Wallet.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> getReferenceByTypeIsIn(Set<RoleType> types);
}
