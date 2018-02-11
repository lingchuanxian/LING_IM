package cn.lingcx.im.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2018-2-11 14:15
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
@Entity
public class LoginUser {
	@Id(autoincrement = true)
	private Long id;
	@NotNull
	@Unique
	@Property(nameInDb = "account")
	private String account;
	@NotNull
	@Property(nameInDb = "password")
	private String password;
	@Generated(hash = 1735684968)
	public LoginUser(Long id, @NotNull String account, @NotNull String password) {
		this.id = id;
		this.account = account;
		this.password = password;
	}
	@Generated(hash = 1159929338)
	public LoginUser() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return this.account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
