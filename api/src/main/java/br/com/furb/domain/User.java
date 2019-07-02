package br.com.furb.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "TB_USER")
@AllArgsConstructor
@NoArgsConstructor
public class User extends IdentityCommonObject{
	private static final long serialVersionUID = 1L;

    @Column(name = "USU_NAME")
    private String username;

    @Column(name = "USU_LOGIN", unique = true)
    private String login;

    @Column(name = "USU_PASSWORD", unique = true)
    private String password;

    @Column(name = "USU_EMAIL", unique = true)
    private String email;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserProfile> userProfiles;
}
