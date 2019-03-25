package kyosu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import exeption.KyosunotExistException;

public class KyosuList {
	private Vector<Kyosu> kyosuVector;

	public KyosuList() {
		this.kyosuVector = new Vector<Kyosu>();
	}

	public void readFromFile() throws FileNotFoundException {
		File file = new File("data/Kyosu.txt");
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			Kyosu kyosu = new Kyosu();
			kyosu.readFromfile(scanner);
			this.kyosuVector.add(kyosu);
		}
		scanner.close();
	}

	public String getKyosuName(int kyosuID) throws KyosunotExistException {
		for (Kyosu kyosu : this.kyosuVector) {// 모든 교수 하나하나
			if (kyosu.getId() == kyosuID) {
				return kyosu.getName();
			}
		}
		throw new KyosunotExistException(kyosuID);//그냥 뜨로우와같이 작용하는듯. 캐치하는곳에 area가있음.
		//예외를 깔끔하게 만들고싶지만, 여기에 에리아 넣기는 왜안되는지 모르겠는데, 안된다.
		//강좌에 있는 이거 바로위에 에리아가 연결되있어서, 여기에넣어봤는데, 돌아가질않는다.
	}
}
