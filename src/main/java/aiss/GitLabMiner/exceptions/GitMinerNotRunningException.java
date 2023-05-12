package aiss.GitLabMiner.exceptions;


public class GitMinerNotRunningException extends RuntimeException {

    public GitMinerNotRunningException() {
        super();
    }
    public GitMinerNotRunningException(String msg) {
        super(msg);
    }
}
