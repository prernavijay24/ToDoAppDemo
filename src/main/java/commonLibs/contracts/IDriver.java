package commonLibs.contracts;

public interface IDriver {
	
	public void navigateToUrl(String url) throws Exception;
	
	public void refresh() throws Exception;
	
	public void navigateBack() throws Exception;
	
	public void navigateForward() throws Exception;
	
	public String getTitle() throws Exception;
	
	public String getCurrentURl() throws Exception;
	
	public void close() throws Exception;
	
	public void closeAllBrowser() throws Exception;
		
}
