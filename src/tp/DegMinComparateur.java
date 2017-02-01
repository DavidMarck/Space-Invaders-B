package tp;

import java.util.Comparator;

/**
 * Compare deux armes selon leurs dégâts minimums
 * @author user-cnam
 *
 */
public class DegMinComparateur implements Comparator<Arme> {

	@Override
	public int compare(Arme a1, Arme a2) {
		if(a1.getMinDegat() > a2.getMinDegat())
		{
			return -1;
		}
		return 1;
	}

}
