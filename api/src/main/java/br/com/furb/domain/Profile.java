package br.com.furb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_PROFILE")
public class Profile extends IdentityCommonObject{
	private static final long serialVersionUID = 1L;

	@Column(name = "PRO_NAME", nullable = false)
    private String name;

    @Column(name = "PRO_ADM")
    private boolean admin;

    @Column(name = "PRO_ACTIVE")
    private boolean active;
}
