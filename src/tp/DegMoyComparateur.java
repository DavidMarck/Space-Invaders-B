package tp;

import java.util.Comparator;

/**
 * Compare deux armes selon leurs dégâts moyens
 * @author user-cnam
 *
 */
public class DegMoyComparateur implements Comparator<Arme> {

	@Override
	public int compare(Arme a1, Arme a2) {
		if(a1.getMoyDegats() > a2.getMoyDegats())
		{
			return -1;
		}
		return 1;
	}

}
