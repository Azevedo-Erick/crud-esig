package crudESIG.utils;

public class LogMessages {
	public static void sucessConnect() {
		System.out.println("======================================================");
		System.out.println("| Conexão com o banco de dados realizada com sucesso |");
		System.out.println("======================================================");
	}

	public static void closingConnection() {
		System.out.println("====================");
		System.out.println("| Fechando conexão |");
		System.out.println("====================");
	}

	public static void errorOnConnection() {
		System.out.println("================================");
		System.out.println("| Erro ao conectar ao postgres |");
		System.out.println("================================");
	}

	public static void driverError() {
		System.out.println("==============================================================");
		System.out.println("| Verifique o driver de conexão, a classe não foi encontrada |");
		System.out.println("==============================================================");
	}

	public static void dataFilteringError() {
		System.out.println("==============================================");
		System.out.println("| Houve algum problema na filtragem de dados |");
		System.out.println("==============================================");
	}

	public static void sucessOnTablesCreation() {
		System.out.println("==============================================================");
		System.out.println("| As tabelas foram criadas com sucesso ou já estavam criadas |");
		System.out.println("==============================================================");
	}

	public static void errorOnTablesCreation() {
		System.out.println("============================");
		System.out.println("| Erro ao criar as tabelas |");
		System.out.println("============================");
	}

	public static void noNewData() {
		System.out.println("===================");
		System.out.println("| Sem novos dados |");
		System.out.println("===================");
	}

	public static void errorOnTaskSearch() {
		System.out.println("=============================");
		System.err.println("| Erro na busca das tarefas |");
		System.out.println("=============================");
	}

	public static void dataInsertWithSucess() {
		System.out.println("=====================================");
		System.out.println("| A tarefa foi inserida com sucesso |");
		System.out.println("=====================================");
	}

	public static void dataCheckProblem() {
		System.out.println("===================================");
		System.out.println("| Não passou na checagem de dados |");
		System.out.println("===================================");
	}

	public static void errorOnSaveTask() {
		System.out.println("===========================");
		System.err.println("| Erro ao salvar a tarefa |");
		System.out.println("===========================");
	}

	public static void dataUpdated() {
		System.out.println("=====================");
		System.out.println("| Dados Atualizados |");
		System.out.println("=====================");
	}

	public static void dataUpdatedProblem() {
		System.out.println("============================================================");
		System.err.println("| Não consegui, houveram problemas para atualizar a tarefa |");
		System.out.println("============================================================");
	}

	public static void dataRemoved() {
		System.out.println("=====================================");
		System.out.println("| A tarefa foi deletada com sucesso |");
		System.out.println("=====================================");
	}

	public static void dataRemovedProblem() {
		System.out.println("==============================");
		System.out.println("| Ocorreu um erro ao excluir |");
		System.out.println("==============================");
	}

	public static void taskFinish() {
		System.out.println("=====================");
		System.out.println("| Tarefa Finalizada |");
		System.out.println("=====================");
	}

	public static void taskFinishProblem() {
		System.out.println("===========================================================");
		System.err.println("| Não consegui houveram problemas para atualizar a tarefa |");
		System.out.println("===========================================================");
	}

}
