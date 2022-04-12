package utils;

public class BuildResult<ResultTypeObject> {
    public Boolean isSuccess = false;
    public Boolean isFailure = false;
    private ResultTypeObject object;

    public BuildResult<ResultTypeObject> success(ResultTypeObject object) {
        this.object = object;
        isFailure = false;
        isSuccess = true;
        return this;
    }

    public BuildResult<ResultTypeObject> failure(ResultTypeObject object) {
        this.object = object;
        isSuccess = false;
        isFailure = true;
        return this;
    }

    public ResultTypeObject getData() {
        return object;
    }
}
