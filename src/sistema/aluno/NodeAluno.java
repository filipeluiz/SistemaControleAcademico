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
public class NodeAluno {
    private NodeAluno prev;
    private Aluno info;
    private NodeAluno next;
    
    public NodeAluno(Aluno value) {
        this.info = value;
    }
    public void setInfo(Aluno value) {
        this.info = value;
    }
    public Aluno getInfo() {
        return this.info;
    }
    public void setNext(NodeAluno n) {
        this.next = n;
    }
    public NodeAluno getNext() {
        return this.next;
    }
    public void setPrev(NodeAluno n) {
        this.prev = n;
    }
    public NodeAluno getPrev() {
        return this.prev;
    }        
}
