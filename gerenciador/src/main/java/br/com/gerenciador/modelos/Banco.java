package br.com.gerenciador.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Banco {
    
    private static final List<Empresa> listaEmpresas = new ArrayList<>();
    private static Integer chaveSequencial = 1;
    
    static {
        Empresa empresa = new Empresa();
        empresa.setNome("Alura");
        Empresa empresa2 = new Empresa();
        empresa2.setNome("Caelum");
        
        Banco banco = new Banco();
        banco.adiciona(empresa);
        banco.adiciona(empresa2);
    }
    
    public void adiciona(Empresa empresa){
        empresa.setId(Banco.chaveSequencial++);
        Banco.listaEmpresas.add(empresa);
    }
    
    public void remove(Integer id){
        listaEmpresas.removeIf(empresa -> empresa.getId().equals(id));
    }
    
    public Empresa buscaPorId(Integer id){
        return listaEmpresas.stream()
                .filter(empresa -> empresa.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    public List<Empresa> getEmpresas(){
        return Banco.listaEmpresas;
    }
}
