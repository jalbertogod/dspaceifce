package org.dspace.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.dspace.eperson.EPerson;
import org.dspace.app.util.Util;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "like")
public class LikeMessage
{
	private boolean erro;
	private String mensagem;
	private int countLike;
	private String handle1;
	private String handle2;
	private int idLike;
    private boolean okay;
    private boolean authenticated;


    public LikeMessage() {
        setOkay(true);
        setErro(true);
        setAuthenticated(false);
    }

    @JsonProperty("okay")
    public boolean isOkay()
    {
        return this.okay;
    }

    public void setOkay(boolean okay)
    {
        this.okay = okay;
    }

    @JsonProperty("authenticated")
    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @JsonProperty("erro")
    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    @JsonProperty("mensagem")
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @JsonProperty("idLike")
    public int getIdLike() {
        return this.idLike;
    }

    public void setIdLike(int idLike) {
        this.idLike = idLike;
    }
    @JsonProperty("countLike")
    public int getCountLike() {
        return this.countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    @JsonProperty("handle1")
    public String getHandle1() {
        return this.handle1;
    }

    public void setHandle1(String handle1) {
        this.handle1 = handle1;
    }

    @JsonProperty("handle2")
    public String getHandle2() {
        return this.handle2;
    }

    public void setHandle2(String handle2) {
        this.handle2 = handle2;
    }
}
