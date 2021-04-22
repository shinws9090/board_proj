package board_proj;


public class TestMain {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//일반적인생성
		Sum s1 = new Sum();

		//클래스 타입을 사용하여 특정 클래스 객체를 생성
		String className="board_proj.Sum";
		
		Class<?> cls = Class.forName(className);
		
		Sum s = (Sum) cls.newInstance();//new Sum();같은기능
		s.add(5, 3);
		
		
		
	}
}
