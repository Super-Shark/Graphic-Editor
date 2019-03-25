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
		for (Kyosu kyosu : this.kyosuVector) {// ��� ���� �ϳ��ϳ�
			if (kyosu.getId() == kyosuID) {
				return kyosu.getName();
			}
		}
		throw new KyosunotExistException(kyosuID);//�׳� �߷ο�Ͱ��� �ۿ��ϴµ�. ĳġ�ϴ°��� area������.
		//���ܸ� ����ϰ� ����������, ���⿡ ������ �ֱ�� �־ȵǴ��� �𸣰ڴµ�, �ȵȴ�.
		//���¿� �ִ� �̰� �ٷ����� �����ư� ������־, ���⿡�־�ôµ�, ���ư����ʴ´�.
	}
}
