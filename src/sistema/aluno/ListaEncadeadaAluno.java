/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.aluno;

/**
 *
 * @author filipe
 */
public class ListaEncadeadaAluno {
    private NodeAluno first;
    private NodeAluno last;
    private int qtd;

    private boolean isEmpty() {
        if(this.qtd == 0) {
          return true;
        }
        else {
          return false;
        }
    }

    public NodeAluno buscar(String cpf) {
      NodeAluno aux = this.first;

      if(this.first == null) {
        return null;
      }
      else {
        while(aux != null) {
          if(aux.getInfo().getCpf().compareToIgnoreCase(cpf) == 0){
            return aux;
          }
          else if(aux.getInfo().getCpf().compareToIgnoreCase(cpf) > 0) {
            return aux;
          }
          else {
            aux = aux.getNext();
          }
        }
        return null;
      }  
    }    
    
    
    public void inserirOrdenado(Aluno value) {
        NodeAluno novo = new NodeAluno(value);
        NodeAluno aux1, aux2;

        if(this.isEmpty() == true) {
            this.first = novo;
            this.last = novo;
            this.qtd = 1;
        }
        else if(value.getCpf().compareToIgnoreCase(this.first.getInfo().getCpf()) < 0) {
            novo.setNext(this.first);
            this.first = novo;
            this.qtd++;
        }
        else if(value.getCpf().compareToIgnoreCase(this.first.getInfo().getCpf()) > 0) {
            this.last.setNext(novo);
            this.last = novo;
            this.qtd++;
        } 
        else {
            aux1 = this.first;
            aux2 = aux1.getNext();

            while(aux2 != null) {
                if(aux2.getInfo().getCpf().compareToIgnoreCase(value.getCpf()) > 0) {
                    aux1.setNext(novo);
                    novo.setNext(aux2);
                    this.qtd++;
                    return;
                }
                else {
                    aux1 = aux1.getNext();
                    aux2 = aux2.getNext();
                }
            }
        }
    }
    
    public void remover(String cpf) { 
        NodeAluno aux, aux1, aux2;

        if(this.first == null) {
            System.out.println("Não é possivel remover, pois a lista está vazia!");
        }
        else {
            aux = buscar(cpf);
            if(aux == null) {
                System.out.println("Não encontrado.");
            }
            else {
                if(aux.getInfo().getCpf().compareToIgnoreCase(cpf) == 0) {
                    if(this.first.getNext() == null) {
                        this.first = null;
                        this.last = null;
                        System.out.println("Removido com sucesso.");
                    }
                    else if(aux == this.first) {
                        this.first = this.first.getNext();
                        this.first.setPrev(null);
                        System.out.println("Removido com sucesso.");
                    }
                    else if(aux == this.last) {
                        this.last = this.last.getPrev();
                        this.last.setNext(null);
                        System.out.println("Removido com sucesso.");
                    }
                    else {
                        aux1 = aux.getPrev();
                        aux2 = aux.getNext();
                        aux1.setNext(aux2);
                        aux2.setPrev(aux1);
                        System.out.println("Removido com sucesso.");
                    }
                }
                else {
                    System.out.println("Não encontrado.");
                }
            }
        }
    }    

    public void exibir() {
        NodeAluno aux = this.first;

        if(this.isEmpty() == true) {
            System.out.println("Lista vazia.");
        }
        else {
            while(aux != null) {
              System.out.println(aux.getInfo() + " ");
              aux = aux.getNext();
            }
        }
    }       
}
