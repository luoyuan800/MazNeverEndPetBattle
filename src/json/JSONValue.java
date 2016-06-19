package json;

/**
 * Created by luoyuan on 2016/6/18.
 */
public class JSONValue<T> {
    T value;
    public T getValue(){
        return value;
    }
    public void setValue(T value){
        this.value = value;
    }
}
