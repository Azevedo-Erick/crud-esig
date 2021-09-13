package crudESIG.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

import crudESIG.utils.LogMessages;

public class ConnectionController implements Serializable {
	private static final long serialVersionUID = 1L;
	private String url;
	private String user;
	private String password;
	private Connection con;

	ConnectionController() {

		url = "jdbc:postgresql://localhost:5432/esigproject";
		user = "";
		password = "";

	}

	public void conectar() {
		try {

			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, password);
			LogMessages.sucessConnect();

		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof ClassNotFoundException) {
				LogMessages.driverError();
			} else {
				LogMessages.errorOnConnection();
			}
		}
	}

	public void desconectar() {
		if (con != null) {
			try {
				con.close();
				LogMessages.closingConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}
