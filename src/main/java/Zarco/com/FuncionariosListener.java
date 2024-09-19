package Zarco.com;

import java.util.List;

public interface FuncionariosListener {
    void onFuncionariosLoadSuccess(List<ModeloFuncionarios> funcionariosList);
    void onFuncionariosLoadFailed (String mensagem);
}
