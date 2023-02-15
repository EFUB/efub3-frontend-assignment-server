package web.seminar.exception;

public class UserExistedException extends IllegalArgumentException{
    public UserExistedException(){
        super("이미 존재하는 유저입니다.");
    }
}
