package graphique;

public class Automaton {

    private static Automaton instance;
    private Boolean closed1 = true;
    private Boolean listen = true;
    private Boolean estab = false;
    private Boolean closing = false;
    private Boolean timewait = false;
    private Boolean synsent = false;
    private Boolean closewait = false;
    private Boolean lastack = false;
    private Boolean closed2 = false;
    private Boolean synrcvd = false;
    private Boolean finwait1 = false;
    private Boolean finwait2 = false;
    private Boolean boxStepByStep;
    private Boolean AutoAck = false;
    private Boolean StepByStep = false;

    public Automaton() {
        this.boxStepByStep = false;
    }

    public static Automaton getInstance() {
        if (null == instance) {
            instance = new Automaton();
        }
        return instance;
    }

    public Boolean getClosed1() {
        return closed1;
    }

    public void setClosed1(Boolean closed1) {
        this.closed1 = closed1;
    }

    public Boolean getListen() {
        return listen;
    }

    public void setListen(Boolean listen) {
        this.listen = listen;
    }

    public Boolean getEstab() {
        return estab;
    }

    public void setEstab(Boolean estab) {
        this.estab = estab;
    }

    public Boolean getClosing() {
        return closing;
    }

    public void setClosing(Boolean closing) {
        this.closing = closing;
    }

    public Boolean getTimewait() {
        return timewait;
    }

    public void setTimewait(Boolean timewait) {
        this.timewait = timewait;
    }

    public Boolean getSynsent() {
        return synsent;
    }

    public void setSynsent(Boolean synsent) {
        this.synsent = synsent;
    }

    public Boolean getClosewait() {
        return closewait;
    }

    public void setClosewait(Boolean closewait) {
        this.closewait = closewait;
    }

    public Boolean getLastack() {
        return lastack;
    }

    public void setLastack(Boolean lastack) {
        this.lastack = lastack;
    }

    public Boolean getClosed2() {
        return closed2;
    }

    public void setClosed2(Boolean closed2) {
        this.closed2 = closed2;
    }

    public Boolean getSynrcvd() {
        return synrcvd;
    }

    public void setSynrcvd(Boolean synrcvd) {
        this.synrcvd = synrcvd;
    }

    public Boolean getFinwait1() {
        return finwait1;
    }

    public void setFinwait1(Boolean finwait1) {
        this.finwait1 = finwait1;
    }

    public Boolean getFinwait2() {
        return finwait2;
    }

    public void setFinwait2(Boolean finwait2) {
        this.finwait2 = finwait2;
    }

    public static void setInstance(Automaton instance) {
        Automaton.instance = instance;
    }

    public Boolean getboxStepByStep() {
        return boxStepByStep;
    }

    public void boxSetStepByStep(Boolean boxStepByStep) {
        this.boxStepByStep = boxStepByStep;
    }

    public Boolean getAutoAck() {
        return AutoAck;
    }

    public void setAutoAck(Boolean AutoAck) {
        this.AutoAck = AutoAck;
    }

    public Boolean getStepByStep() {
        return StepByStep;
    }

    public void setStepByStep(Boolean stepByStep) {
        StepByStep = stepByStep;
    }
}
