package crudESIG.controller;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import crudESIG.model.TaskModel;
import crudESIG.utils.LogMessages;

@Named
@ViewScoped
public class TaskController implements Serializable {
	ConnectionController con = new ConnectionController();
	private TaskModel task;
	private TaskModel taskToFilter = new TaskModel(0, "", false, "");
	private List<TaskModel> taskList = new ArrayList<TaskModel>();
	private List<TaskModel> taskListFilter = new ArrayList<TaskModel>();

	/* FILTRO PARA LISTAGEM DE TAREFAS */

	public void filter() {
		try {
			if (this.getTaskToFilter().getId() != 0 || !this.getTaskToFilter().getTitulo().isBlank()
					|| !this.getTaskToFilter().getResponsavel().isEmpty()) {
				this.getTaskListFilter().clear();
				for (TaskModel obj : taskList) {
					if (!this.getTaskListFilter().contains(obj)) {
						if (this.getTaskToFilter().getId() == obj.getId()
								|| this.getTaskToFilter().getTitulo().trim().equals(obj.getTitulo())
								|| this.getTaskToFilter().getResponsavel().equals(obj.getPrioridade())) {
							this.getTaskListFilter().add(obj);
						}
					}

				}
			} else {
				this.getTaskListFilter().clear();
				this.getTaskListFilter().addAll(taskList);

			}
		} catch (Exception e) {
			e.printStackTrace();
			LogMessages.dataFilteringError();
		}
	}

	/* CHECAGEM DOS CAMPOS DE ENVIO */
	public boolean checkFields() {
		if (this.getTask().getDescricao().trim().equals("") || this.getTask().getDeadLine().trim().equals("")
				|| this.getTask().getTitulo().trim().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/* GET'S E SET'S */
	public List<TaskModel> getTaskListFilter() {
		return taskListFilter;
	}

	public void setTaskListFilter(List<TaskModel> taskListFilter) {
		this.taskListFilter = taskListFilter;
	}

	public TaskModel getTaskToFilter() {
		return taskToFilter;
	}

	public void setTaskToFilter(TaskModel taskToFilter) {
		this.taskToFilter = taskToFilter;
	}

	public TaskModel getTask() {
		if (this.task == null)
			this.setTask(new TaskModel());
		return this.task;
	}

	public void setTask(TaskModel task) {
		this.task = task;
	}

	public void printData() {
	}

	public List<TaskModel> getTaskList() {
		if (this.taskList == null)
			this.setTaskList(new ArrayList<TaskModel>());
		return this.taskList;
	}

	public void setTaskList(List<TaskModel> taskList) {
		this.taskList = taskList;
	}

	/* INICIO USO ATIVO DO BANCO DE DADOS */
	public void criarTabela() {
		String create = "CREATE TABLE IF NOT EXISTS public.tarefa (" + "idtarefa serial NOT NULL,"
				+ "titulo varchar NOT NULL," + "responsavel varchar NOT NULL," + "descricao varchar NOT NULL,"
				+ "prioridade varchar NOT NULL," + "deadline varchar NOT NULL," + "situacao boolean NOT NULL,"
				+ "PRIMARY KEY (idtarefa));";

		try {
			con.conectar();
			Statement newTable = con.getCon().createStatement();

			newTable.executeUpdate(create);
			newTable.close();
			con.desconectar();
			LogMessages.sucessOnTablesCreation();
		} catch (Exception e) {
			e.printStackTrace();
			LogMessages.errorOnTablesCreation();
		}

	}

	public void searchForAll() {
		String query = "SELECT * FROM tarefa";

		try {
			this.criarTabela();

			if (this.getTaskList().isEmpty()) {

				this.getTaskListFilter().clear();
				ConnectionController con = new ConnectionController();
				con.conectar();
				PreparedStatement tarefas = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet res = tarefas.executeQuery();

				// Pegar a última linha que tiver dados
				res.last();
				int qtdLinhas = res.getRow();
				res.beforeFirst();

				if (qtdLinhas > 0) {
					while (res.next()) {
						taskList.add(
								new TaskModel(Integer.parseInt(res.getString(1)), res.getString(2), res.getString(4),
										res.getBoolean(7), res.getString(3), res.getString(5), res.getString(6)));
					}
					this.getTaskListFilter().addAll(taskList);
				}
				con.desconectar();
			} else {
				LogMessages.noNewData();
			}
			con.desconectar();

		} catch (Exception e) {
			e.printStackTrace();
			LogMessages.errorOnTaskSearch();
		}
	}

	public void insertData() {
		try {
			if (this.checkFields()) {

				String INSERT = "INSERT INTO tarefa (titulo, responsavel,descricao, prioridade,deadline,situacao) VALUES(?,?,?,?,?,?)";
				ConnectionController con = new ConnectionController();
				con.conectar();
				PreparedStatement salvar = con.getCon().prepareStatement(INSERT);
				salvar.setString(1, this.getTask().getTitulo());
				salvar.setString(2, this.getTask().getResponsavel());
				salvar.setString(3, this.getTask().getDescricao());
				salvar.setString(4, this.getTask().getPrioridade());
				salvar.setString(5, this.getTask().getDeadLine());
				salvar.setBoolean(6, this.getTask().isStatus());
				salvar.executeUpdate();
				salvar.close();
				con.desconectar();

				LogMessages.dataInsertWithSucess();

				this.clearData();
				this.taskList.clear();
				this.searchForAll();
			} else {
				LogMessages.dataCheckProblem();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogMessages.errorOnSaveTask();
		}
	}

	// Atualizar 1/2
	public void updateData(TaskModel taskToUpdate) {
		this.setTask(taskToUpdate);

	}

	// Atualizar 2/2
	public void updateData() {

		if (this.checkFields()) {
			try {
				ConnectionController con = new ConnectionController();
				con.conectar();
				String UPDATE = "UPDATE tarefa SET titulo=?, responsavel=?,descricao=?, prioridade=?,deadline=?,situacao=? WHERE idtarefa=?";
				PreparedStatement upd = con.getCon().prepareStatement(UPDATE);

				upd.setString(1, this.getTask().getTitulo());
				upd.setString(2, this.getTask().getResponsavel());
				upd.setString(3, this.getTask().getDescricao());
				upd.setString(5, this.getTask().getDeadLine());
				upd.setString(4, this.getTask().getPrioridade());
				upd.setBoolean(6, this.getTask().isStatus());
				upd.setInt(7, this.getTask().getId());
				upd.executeUpdate();
				upd.close();
				this.clearData();
				this.taskList.clear();
				this.searchForAll();
				LogMessages.dataUpdated();

			} catch (Exception e) {
				e.printStackTrace();
				LogMessages.dataUpdatedProblem();
			}
		} else {
			LogMessages.dataCheckProblem();
		}
	}

	public void delete(int id) {
		String DELETE = "DELETE FROM tarefa WHERE idtarefa=?";
		String SEARCH_FOR_ID = "SELECT * FROM tarefa WHERE idtarefa=?";

		try {

			con.conectar();
			PreparedStatement task = con.getCon().prepareStatement(SEARCH_FOR_ID, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			task.setInt(1, id);
			ResultSet res = task.executeQuery();

			res.last();
			int qtd = res.getRow();
			res.beforeFirst();

			if (qtd > 0) {
				PreparedStatement del = con.getCon().prepareStatement(DELETE);
				del.setInt(1, id);
				del.executeUpdate();
				del.close();
				con.desconectar();
				LogMessages.dataRemoved();
				this.getTaskList().clear();
				this.searchForAll();
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogMessages.dataRemovedProblem();
		}
	}

	public void finish(TaskModel tk) {

		int id = tk.getId();
		try {
			ConnectionController con = new ConnectionController();
			con.conectar();

			String UPDATE = "UPDATE tarefa SET  situacao=? WHERE idtarefa=?";
			PreparedStatement upd = con.getCon().prepareStatement(UPDATE);

			upd.setBoolean(1, true);
			upd.setInt(2, id);

			upd.executeUpdate();
			upd.close();
			con.desconectar();
			LogMessages.taskFinish();
			this.taskList.clear();
			this.searchForAll();

		} catch (Exception e) {
			e.printStackTrace();
			LogMessages.taskFinishProblem();
		}
	}
	/* FIM USO ATIVO DO BANCO DE DADOS */

	private void clearData() {
		this.setTask(null);
	}

	private static final long serialVersionUID = 1L;

}
