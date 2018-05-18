package com.blogdns.nonato.gedoc.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {
	private String chave;
    private String area;
    private String equipe;
    private String senha;
    private Boolean logado;
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getEquipe() {
		return equipe;
	}
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean getLogado() {
		return logado;
	}
	public void setLogado(Boolean logado) {
		this.logado = logado;
	}
	public String autenticacaoUsuario() {
        
        /*RequestContext rcontext = RequestContext.getCurrentInstance();
        FacesMessage mEmbarque = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                "<h5>Equipe não embarcada nessa data."
                        + "<br/><center>Entre com outra Equipe.</center></h5>");
        FacesMessage mCadastro = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                "<h5><br/><center>Usuário não cadastrado.</center></h5>");
        
        FacesMessage mSenha = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                "<h5><br/><center>Senha inválida.</center></h5>");
        
        
        
        String turno = Metodos.retornaTurno(equipe);
        //Operador operador = ejb.getOperadorByChave(chave);
        String chave = "bshn";
        
        if(chave!=null) {
            if(turno==null) {
                rcontext.showMessageInDialog(mEmbarque);
                return null;
            }
            if("1234".equals(senha)) {
                //operadorLogado = operador;
            } else {
                rcontext.showMessageInDialog(mSenha);
                return  null;
            }
        } else {
            rcontext.showMessageInDialog(mCadastro);
            return  null;
        } 
        
        rcontext.execute("PF('dlgLogin').hide()");
        logado=true;
        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());*/
        return "index";
    }
    public String logout() {
        
        /*RequestContext.getCurrentInstance().reset("formStatus");
        logado=false;*/
        return null;
    }
}

/**
 * 
    
    /*public void abriDialogo() {
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("width", 350);
        options.put("height", 220);
        options.put("contentHeight",200);
        options.put("contentWidth", 300);

        RequestContext.getCurrentInstance().openDialog("login",options, null);
    }
    
    public void fechaDialogo() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }*/