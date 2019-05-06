package net.mypos.MyProject.Model;

import java.io.Serializable;

import net.mypos.MyProjectBackend.dto.Admin;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.Staff;
import net.mypos.MyProjectBackend.dto.Supplier;

public class UserModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String fullName;
	private String email;
	private String role;
	private Cart cart;
	//private Admin admin;
	//private Staff staff;
	//private Supplier supplier;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	
	/*public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}*/
	
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", fullName=" + fullName + ", email=" + email + ", role=" + role + ", cart="
				+ cart + "]";
	}
	
	
}
