package ralph.lydia.results;

import java.util.List;
import java.util.ArrayList;

public class ResultsTableImpl implements ResultsTable {
	ConstituencyResult constituencyResult;
	List<ResultModel> concatenatedResults;

	// Constructor
	public ResultsTableImpl() {
		constituencyResult = new ConstituencyResult();
		concatenatedResults = new ArrayList<ResultModel>();

		for (PartyCode pc : PartyCode.values()) {
			ResultModel result = new ResultModel();
			result.setPartyCode(pc);
			result.setShare(0);
			result.setVotes(0);
			concatenatedResults.add(result);
		}
		this.constituencyResult.setResultList(concatenatedResults);
	}

	/**
	 * TODO:Improve
	 * Consider using JSPs?
	 */
	public void displayResults() {
		System.out.println("Running total:");
		this.constituencyResult.sortDescendingVotes();
		
		for (ResultModel result : this.constituencyResult.getResultList()) {
			result.printAllValues();
		}
	}
	
	public void refreshDisplay(ConstituencyResult resultList){
		clearDisplay();
		updateResults(resultList);
		displayResults();
	}

	private void clearDisplay(){
		// TODO
	}
	
	/**
	 * For each result that has just come in, find the matching enum in
	 * concatenatedResults and increase votes and share
	 */
	public void updateResults(ConstituencyResult constitResult) {
		
		System.out.println("\n --- REFRESHING DISPLAY --- \n");

		for (ResultModel concatResult : concatenatedResults) {

			for (ResultModel freshResult : constitResult.getResultList()) {

				if (freshResult.getPartyCode().equals(
						concatResult.getPartyCode())) {
					concatResult.addVotes(freshResult.getVotes());
					concatResult.addShare(freshResult.getShare());
				}
			}
		}
	}
		
		public void sortAscendingOrder(){
			// TODO: use Collections.sort?
		}
		
		public ConstituencyResult getConcatenatedResults(){
			return this.constituencyResult;
		}

}
