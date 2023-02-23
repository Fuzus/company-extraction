package br.com.fuzus;

import br.com.fuzus.model.Company;
import br.com.fuzus.utils.ExcelUtils;
import br.com.fuzus.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) {
            SeleniumUtils driver = new SeleniumUtils("https://cadastroempresa.com.br/empresas/sao-paulo-sp");
            List<Company> companies = new ArrayList<>();
        try {
            do {
                List<WebElement> elements = driver.getAllCompanyLinks();
                for (int i = 0; i < elements.size(); i++) {
                    String cnpj = driver.getCompaniesCnpj(elements.get(i));
                    if (companies.stream().noneMatch(x -> x.getCnpj() == null || x.getCnpj().equals(cnpj))) {
                        driver.click(elements.get(i));
                        Map<String, String> map = new HashMap<>();

                        //informacoes de registro
                        List<WebElement> IRKeys = driver.getAll(By.xpath("//h2[contains(text(), 'Informações de Registro')]/../..//dt"));
                        List<WebElement> IRValues = driver.getAll(By.xpath("//h2[contains(text(), 'Informações de Registro')]/../..//dd/span/p[1]"));
                        for (int j = 0; j < IRValues.size(); j++) {
                            map.put(IRKeys.get(j).getText().trim(), IRValues.get(j).getText().trim());
                        }

                        //Contatos
                        List<WebElement> contactKeys = driver.getAll(By.xpath("//h2[contains(text(), 'Contatos')]/../..//dt"));
                        List<WebElement> contactValues = driver.getAll(By.xpath("//h2[contains(text(), 'Contatos')]/../..//dd/span/span[1]"));
                        for (int j = 0; j < contactValues.size(); j++) {
                            map.put(contactKeys.get(j).getText().trim(), contactValues.get(j).getText().trim());
                        }

                        //localizacao
                        List<WebElement> placeKeys = driver.getAll(By.xpath("//h2[contains(text(), 'Localização')]/../..//dt"));
                        List<WebElement> placeValues = driver.getAll(By.xpath("//h2[contains(text(), 'Localização')]/../..//dd/span/span[1]"));
                        Map<String, String> place = new HashMap<>();
                        for (int j = 0; j < placeValues.size(); j++) {
                            place.put(placeKeys.get(j).getText().trim(), placeValues.get(j).getText().trim());
                        }

                        Company company = new Company();
                        company.setName(map.get("Nome de Fantasia:") + " - " + map.get("Nome Empresarial:"));
                        company.setCnpj(map.get("CNPJ:"));
                        company.setEmail(map.get("E-mail:"));
                        company.getPlace().putAll(place);

                        companies.add(company);

                        driver.goBack();
                        elements = driver.getAllCompanyLinks();
                    }
                }
            } while (driver.clickNext(By.xpath("//*[@id='content-wrapper']//nav[@aria-label='Pagination']//a")));
            driver.killDriver();
            ExcelUtils.writeExcel(companies);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            driver.killDriver();
        }
    }
}
