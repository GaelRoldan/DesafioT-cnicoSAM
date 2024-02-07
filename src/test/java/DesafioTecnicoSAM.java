import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class DesafioTecnicoSAM {

	
	//Declaramos y asignamos URL que sera utilizada a lo largo de los casos
	//Declaramos driver del navegador para su próxima instanciación
	
	private String baseUrl = "https://www.samsistemas.com.ar";
	WebDriver driver;
	
	@BeforeTest(description="Instanciación del driver e ingreso a web de SAM")
	public void setBaseUrl(){
		
		//Enlazamos PATH con driver local para no depender del Selenium Manager, obteniendo mayor velocidad, luego instanciamos driver
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		//Seteo de URL base
		
		driver.get(baseUrl);
	}
	
	@Test(priority= 1, description=" Seleccion lupa de búsqueda, búsqueda \"devops\" , validación de URL correcta y print cantidad de elementos")
	public void searchToggleTest()
	{
		
		//Asignamos la lupa y su input a variables de tipo WebElement, también palabra de búsqueda
		//El driver encuentra los elementos mediante método findElement() y un localizador (tag, class, xpath, id, etc)
		
		WebElement lupaDeBusqueda = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/header/div[1]/div[2]/nav/ul/li[6]/a"));
		
		WebElement inputBusqueda = driver.findElement(By.id("ocean-search-form-1"));
		
		String busqueda = "devops";
		
		//Wait para no generar errores en la espera de despliegues en apartados de la página
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		//seleccion lupa 
		
		lupaDeBusqueda.click();
		
		//Busqueda 
		
		wait.until(ExpectedConditions.elementToBeClickable(inputBusqueda));
		inputBusqueda.sendKeys(busqueda);
		inputBusqueda.submit();
		
		//Validacion apartado correcto
		
		String expectedURL = "https://www.samsistemas.com.ar/?s=devops";
		String actualURL = driver.getCurrentUrl();
		
		Assert.assertEquals(actualURL, expectedURL);
		
		// Imprimir cantidad de elementos: cada elemento está formado por el tag article en este caso, los localizamos y asignamos a una lista, el tamaño de la misma será la cantidad
		
		List<WebElement> elementos = driver.findElements(By.tagName("article"));
		int cantidadElementos = elementos.size();
		
		System.out.println("La cantidad de elementos arrojados por la búsqueda son: " + cantidadElementos);
		
	}
	
	@Test(priority= 2,description=" Ingresar a página 2 del pageNumbers, clickear cuarto artículo, verificar URL correcta y existencia de palabras Test Management")
	public void pageNumbersTest()
	{
		
		//botón pagina 2
		
		WebElement pageNumber2 = driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div/div/div/ul/li[2]/a"));
		
		// Ingresamos a página 2
		
		pageNumber2.click();
		
		// asignamos cuarto artículo y clickeamos
		
		WebElement cuartoArticulo = driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div/div/article[4]/div/div[2]/header/h2/a"));
		
		cuartoArticulo.click();
		
		//Verificacion ingreso correcto
		
		String expectedURL = "https://www.samsistemas.com.ar/servicios-devops/automatizacion-de-pruebas/";
		String actualURL = driver.getCurrentUrl();
		
		Assert.assertEquals(actualURL, expectedURL);
		
		//Validación cadena "Test Management"
		
			// obtenemos el contenido de la página
		
		String contenidoPagina = driver.getPageSource();
		
			// Si el contenido de la página contiene la cadena, devuelve true como se espera
		
		String cadenaEsperada = "Test Management";
		Assert.assertEquals(contenidoPagina.contains(cadenaEsperada), true);
		
		
	}
	
	
	@Test(priority= 3, description=" Selección de item Contacto del menú, selección de opcion dropdown \"Trabaje con nosotros\", validación de habilitación botón [Enviar]")
	public void contactTest()
	{
		// Asignación item Contacto y sub item Trabaje con nosotros
		
		WebElement itemMenuContacto = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/header/div[1]/div[2]/nav/ul/li[5]/a/span"));
		WebElement itemSubMenuTrabajeConNosotros = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/header/div[1]/div[2]/nav/ul/li[5]/ul/li/a"));
		
		// Acción de moverse a item Contacto para desplegar subMenu
		
		Actions accionMoverseA = new Actions(driver);
		
		accionMoverseA.moveToElement(itemMenuContacto).perform();
		
		// Click en sub item trabaje con nosotros
		
		itemSubMenuTrabajeConNosotros.click();
		
		// Validación botón Enviar está habilitado
		
		WebElement btnEnviar = driver.findElement(By.xpath("/html/body/div[1]/div/main/div/section[5]/div[2]/div/div/div[2]/div/div/div/form/p[4]/input"));
		
		Assert.assertEquals(btnEnviar.isEnabled(), true);
		
		
	}
	
	
	@Test(priority= 4, description=" Validación dirección de empresa es correcta en el footer")
	public void footerTest()
	{
		
		// Asignamos apartado contacto del footer
		
		WebElement footerContacto = driver.findElement(By.xpath("/html/body/div[1]/div/footer/div/div[1]/div/div[1]/div/div/p/span"));
		
		// Asignamos el texto que posee el footer, la direccion esperada y el booleano actual dada la condición de que la dirección contiene la esperada
		
		String footerContactoActual = footerContacto.getText();
		
		String direccionEmpresaEsperada = "Azcuénaga 1344, piso 1º";
		
		Boolean direccionEmpresaActual = footerContactoActual.contains(direccionEmpresaEsperada);
		
		// Verificación de dirección esperada
		
		Assert.assertEquals(direccionEmpresaActual, true);
		
	
	}
	
	// Cerramos el driver luego de todos los test
	@AfterTest
	public void driverClose() {
		
		driver.close();
	}
	
}
