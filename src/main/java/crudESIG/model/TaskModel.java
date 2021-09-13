package crudESIG.model;

import java.util.Objects;

public class TaskModel implements Cloneable {
	private int id;
	private String titulo;
	private String descricao;
	private boolean status;
	private String responsavel;
	private String prioridade;
	private String deadLine;

	public TaskModel() {

	}

	public TaskModel(int id, String titulo, boolean status, String responsavel) {
		super();
		this.setId(id);
		this.setTitulo(titulo);
		this.setStatus(status);
		this.setResponsavel(responsavel);
	}

	public TaskModel(int id, String titulo, String descricao, boolean status, String prioridade, String deadLine,
			String responsavel) {
		super();
		this.setId(id);
		this.setTitulo(titulo);
		this.setDeadLine(deadLine);
		this.setPrioridade(prioridade);
		this.setStatus(status);
		this.setDescricao(descricao);
		this.setResponsavel(responsavel);
	}

	public TaskModel getClone() {
		try {
			return (TaskModel) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStatusText() {
		if (this.isStatus()) {
			return "Concluído";
		} else {
			return "Em andamento";
		}
	}

	public void setStatus(String status) {
		if (status.equals("true")) {
			this.status = true;
		} else {
			this.status = false;
		}
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	@Override
	public String toString() {
		return ("===================\n" + "ID " + this.getId() + "\n" + "TAREFA: " + this.getTitulo() + "\n"
				+ "RESPONSÁVEL:" + this.getResponsavel() + "\n" + "DESCRIÇÃO: " + this.getDescricao() + "\n"
				+ "PRIORIDADE:" + this.getPrioridade() + "\n" + "DEADLINE: " + this.getDeadLine() + "\n" + "CONCLUÍDA: "
				+ this.isStatus() + "\n" + "===================\n");
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@SuppressWarnings("unused")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TaskModel other = (TaskModel) obj;
		return Objects.equals(id, other.id);
	}
}
