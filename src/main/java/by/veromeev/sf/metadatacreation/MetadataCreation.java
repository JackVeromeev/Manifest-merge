package by.veromeev.sf.metadatacreation;

import by.veromeev.sf.packagemerger.XmlMergerException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;

public class MetadataCreation {
    public MetadataCreation(String oliPath, String qliPath) throws IOException {
        Document oliDocument = getDocument(oliPath);
        Document qliDocument = getDocument(qliPath);
        Set<String> oliFields = new TreeSet<>();
        Set<String> qliFields = new TreeSet<>();
        Set<String> existFields = new TreeSet<>();
        existFields.add("adresscheck_basic__c".toLowerCase());
        existFields.add("adresscheck_person__c".toLowerCase());
        existFields.add("anzahl__c".toLowerCase());
        existFields.add("auszahlung__c".toLowerCase());
        existFields.add("automatische_inkassouebergabe__c".toLowerCase());
        existFields.add("automatische_zuordnung__c".toLowerCase());
        existFields.add("automatischer_kontoauszugabgleich__c".toLowerCase());
        existFields.add("autorisierungsanfragen__c".toLowerCase());
        existFields.add("bankcheck__c".toLowerCase());
        existFields.add("beleglose_buchung_elv__c".toLowerCase());
        existFields.add("beleglose_buchung_disagio_ueb__c".toLowerCase());
        existFields.add("bereitstellung_jaehrlich__c".toLowerCase());
        existFields.add("besondere_vereinbarungen__c".toLowerCase());
        existFields.add("besondere_vereinbarungen_za__c".toLowerCase());
        existFields.add("bezeichnung_des_nachlasses__c".toLowerCase());
        existFields.add("bin_check__c".toLowerCase());
        existFields.add("bin_country_Filter__c".toLowerCase());
        existFields.add("callback__c".toLowerCase());
        existFields.add("chargeback_gebuehr__c".toLowerCase());
        existFields.add("chargeback_light__c".toLowerCase());
        existFields.add("creditcard_check__c".toLowerCase());
        existFields.add("disagio_elv__c".toLowerCase());
        existFields.add("disagio_american_express__c".toLowerCase());
        existFields.add("disagio_aufschlag_com_card_15__c".toLowerCase());
        existFields.add("disagio_aufschlag_non_ewr_cards_5__c".toLowerCase());
        existFields.add("disagio_aufschlag_ohne_3d__c".toLowerCase());
        existFields.add("disagio_aufschlag_risiko_branchen__c".toLowerCase());
        existFields.add("disagio_aufschlag_trx_ungleich_euro__c".toLowerCase());
        existFields.add("disagio_diners_club__c".toLowerCase());
        existFields.add("disagio_eps_oesterreich__c".toLowerCase());
        existFields.add("disagio_giropay_pauschal__c".toLowerCase());
        existFields.add("disagio_jcb__c".toLowerCase());
        existFields.add("disagio_maestro_international__c".toLowerCase());
        existFields.add("disagio_mastercard__c".toLowerCase());
        existFields.add("disagio_sofort_berweisung__c".toLowerCase());
        existFields.add("disagio_visa__c".toLowerCase());
        existFields.add("disagio_vkp__c".toLowerCase());
        existFields.add("disagio_zusatz__c".toLowerCase());
        existFields.add("einrichtungsentgelt_einmalig__c".toLowerCase());
        existFields.add("Freigabe_Level__c".toLowerCase());
        existFields.add("geoip_check__c".toLowerCase());
        existFields.add("infos_zur_aufschaltung_erforderlich__c".toLowerCase());
        existFields.add("infoscore_bonitaetspruefung_1__c".toLowerCase());
        existFields.add("infoscore_bonitaetspruefung_2__c".toLowerCase());
        existFields.add("infoscore_bonitaetspruefung_3__c".toLowerCase());
        existFields.add("integrity_fee__c".toLowerCase());
        existFields.add("ip_country_Filter__c".toLowerCase());
        existFields.add("jaehrliche_bereitstellung_giropay__c".toLowerCase());
        existFields.add("kompletter_einmaliger_rabatt__c".toLowerCase());
        existFields.add("kompletter_monatlicher_rabatt__c".toLowerCase());
        existFields.add("konditionen_onepager__c".toLowerCase());
        existFields.add("kosten_je_buchungsvorgang__c".toLowerCase());
        existFields.add("kosten_je_generierung_collect__c".toLowerCase());
        existFields.add("kosten_je_generierung_invoicing__c".toLowerCase());
        existFields.add("Kosten_pro_Transaktion_1__c".toLowerCase());
        existFields.add("Kosten_pro_Transaktion_2__c".toLowerCase());
        existFields.add("Kosten_pro_Transaktion_3__c".toLowerCase());
        existFields.add("Kosten_pro_Transaktion_4__c".toLowerCase());
        existFields.add("Kosten_pro_Transaktion_5__c".toLowerCase());
        existFields.add("laufzeit_monate__c".toLowerCase());
        existFields.add("manuelle_bearbeitung_von_ueberweisungen__c".toLowerCase());
        existFields.add("mindestdisagio_monatlich__c".toLowerCase());
        existFields.add("monatliche_bereitstellung_kk__c".toLowerCase());
        existFields.add("monatliche_bereitstellung_liste__c".toLowerCase());
        existFields.add("monatliche_bereitstellung_vkp__c".toLowerCase());
        existFields.add("payone_produktkuerzel__c".toLowerCase());
        existFields.add("pcs__c".toLowerCase());
        existFields.add("pos_sperrdatei__c".toLowerCase());
        existFields.add("Product_Name__c".toLowerCase());
        existFields.add("produkt_komponenten_art__c".toLowerCase());
        existFields.add("produktbeschreibung_engl__c".toLowerCase());
        existFields.add("produktbeschreibung_p5112__c".toLowerCase());
        existFields.add("produktfamilie__c".toLowerCase());
        existFields.add("produktname_engl__c".toLowerCase());
        existFields.add("produktname_onepager__c".toLowerCase());
        existFields.add("rabatt_pac_zahlungsart_counter__c".toLowerCase());
        existFields.add("relevanter_plv_bestandteil__c".toLowerCase());
        existFields.add("ruecklastschrift_gebuehr_lastschrift_de__c".toLowerCase());
        existFields.add("r_cklastschrift_geb_hr_lastschrift_eu__c".toLowerCase());
        existFields.add("haendler_selbstauskunft_erforderlich__c".toLowerCase());
        existFields.add("sepa_mandats_download_getfile__c".toLowerCase());
        existFields.add("sepa_mandatseinholung__c".toLowerCase());
        existFields.add("sepa_mandats_pdf__c".toLowerCase());
        existFields.add("sicherheitseinbehalt__c".toLowerCase());
        existFields.add("Staffel_1_Preis_claimsdunning__c".toLowerCase());
        existFields.add("Staffel_1_Preis_claimspostcol__c".toLowerCase());
        existFields.add("Staffel_1_Preis_claimspostsw__c".toLowerCase());
        existFields.add("Staffel_1_Preis_claimstransfer__c".toLowerCase());
        existFields.add("Staffel_1_Preis_glauza__c".toLowerCase());
        existFields.add("Staffel_1_Preis_glmaza__c".toLowerCase());
        existFields.add("Staffel_1_Preis_invoiceglobal__c".toLowerCase());
        existFields.add("Staffel_1_Preis_invoicepostcol__c".toLowerCase());
        existFields.add("Staffel_1_Preis_invoicepostsw__c".toLowerCase());
        existFields.add("Staffel_1_Preis_micropaytrx__c".toLowerCase());
        existFields.add("Staffel_1_Preis_riskadbasic__c".toLowerCase());
        existFields.add("Staffel_1_Preis_riskadperson__c".toLowerCase());
        existFields.add("Staffel_1_Preis_riskbacheck__c".toLowerCase());
        existFields.add("Staffel_1_Preis_riskcccheck__c".toLowerCase());
        existFields.add("Staffel_1_Preis_riskinfobobas__c".toLowerCase());
        existFields.add("Staffel_1_Preis_riskinfoboprem__c".toLowerCase());
        existFields.add("Staffel_1_Preis_riskinfoboprof__c".toLowerCase());
        existFields.add("Staffel_1_Preis_riskposdata__c".toLowerCase());
        existFields.add("Staffel_1_TRX_claimsdunning__c".toLowerCase());
        existFields.add("Staffel_1_TRX_claimspostcolor__c".toLowerCase());
        existFields.add("Staffel_1_TRX_claimspostsw__c".toLowerCase());
        existFields.add("Staffel_1_TRX_claimstransfer__c".toLowerCase());
        existFields.add("Staffel_1_TRX_glauza__c".toLowerCase());
        existFields.add("Staffel_1_TRX_glmaza__c".toLowerCase());
        existFields.add("Staffel_1_TRX_invoiceglobal__c".toLowerCase());
        existFields.add("Staffel_1_TRX_invoicepostcolor__c".toLowerCase());
        existFields.add("Staffel_1_TRX_invoicepostsw__c".toLowerCase());
        existFields.add("Staffel_1_TRX_micropaytrx__c".toLowerCase());
        existFields.add("Staffel_1_TRX_riskadbasic__c".toLowerCase());
        existFields.add("Staffel_1_TRX_riskadperson__c".toLowerCase());
        existFields.add("Staffel_1_TRX_riskbacheck__c".toLowerCase());
        existFields.add("Staffel_1_TRX_riskcccheck__c".toLowerCase());
        existFields.add("Staffel_1_TRX_riskinfoboprem__c".toLowerCase());
        existFields.add("Staffel_1_TRX_riskinfoboprof__c".toLowerCase());
        existFields.add("Staffel_2_Preis_claimsdunning__c".toLowerCase());
        existFields.add("Staffel_2_Preis_claimspostcol__c".toLowerCase());
        existFields.add("Staffel_2_Preis_claimspostsw__c".toLowerCase());
        existFields.add("Staffel_2_Preis_claimstransfer__c".toLowerCase());
        existFields.add("Staffel_2_Preis_glauza__c".toLowerCase());
        existFields.add("Staffel_2_Preis_glmaza__c".toLowerCase());
        existFields.add("Staffel_2_Preis_invoiceglobal__c".toLowerCase());
        existFields.add("Staffel_2_Preis_invoicepostcol__c".toLowerCase());
        existFields.add("Staffel_2_Preis_invoicepostsw__c".toLowerCase());
        existFields.add("Staffel_2_Preis_micropaytrx__c".toLowerCase());
        existFields.add("Staffel_2_Preis_riskadbasic__c".toLowerCase());
        existFields.add("Staffel_2_Preis_riskadperson__c".toLowerCase());
        existFields.add("Staffel_2_Preis_riskbacheck__c".toLowerCase());
        existFields.add("Staffel_2_Preis_riskcccheck__c".toLowerCase());
        existFields.add("Staffel_2_Preis_riskinfoboprem__c".toLowerCase());
        existFields.add("Staffel_2_Preis_riskinfoboprof__c".toLowerCase());
        existFields.add("Staffel_2_TRX_claimsdunning__c".toLowerCase());
        existFields.add("Staffel_2_TRX_claimspostcolor__c".toLowerCase());
        existFields.add("Staffel_2_TRX_claimspostsw__c".toLowerCase());
        existFields.add("Staffel_2_TRX_claimstransfer__c".toLowerCase());
        existFields.add("Staffel_2_TRX_glauza__c".toLowerCase());
        existFields.add("Staffel_2_TRX_glmaza__c".toLowerCase());
        existFields.add("Staffel_2_TRX_invoiceglobal__c".toLowerCase());
        existFields.add("Staffel_2_TRX_invoicepostcolor__c".toLowerCase());
        existFields.add("Staffel_2_TRX_invoicepostsw__c".toLowerCase());
        existFields.add("Staffel_2_TRX_micropaytrx__c".toLowerCase());
        existFields.add("Staffel_2_TRX_riskadbasic__c".toLowerCase());
        existFields.add("Staffel_2_TRX_riskadperson__c".toLowerCase());
        existFields.add("Staffel_2_TRX_riskbacheck__c".toLowerCase());
        existFields.add("Staffel_2_TRX_riskcccheck__c".toLowerCase());
        existFields.add("Staffel_2_TRX_riskinfoboprem__c".toLowerCase());
        existFields.add("Staffel_2_TRX_riskinfoboprof__c".toLowerCase());
        existFields.add("Staffel_3_Preis_claimsdunning__c".toLowerCase());
        existFields.add("Staffel_3_Preis_claimspostcol__c".toLowerCase());
        existFields.add("Staffel_3_Preis_claimspostsw__c".toLowerCase());
        existFields.add("Staffel_3_Preis_claimstransfer__c".toLowerCase());
        existFields.add("Staffel_3_Preis_glauza__c".toLowerCase());
        existFields.add("Staffel_3_Preis_glmaza__c".toLowerCase());
        existFields.add("Staffel_3_Preis_invoiceglobal__c".toLowerCase());
        existFields.add("Staffel_3_Preis_invoicepostcol__c".toLowerCase());
        existFields.add("Staffel_3_Preis_invoicepostsw__c".toLowerCase());
        existFields.add("Staffel_3_Preis_micropaytrx__c".toLowerCase());
        existFields.add("Staffel_3_Preis_riskadbasic__c".toLowerCase());
        existFields.add("Staffel_3_Preis_riskadperson__c".toLowerCase());
        existFields.add("Staffel_3_Preis_riskbacheck__c".toLowerCase());
        existFields.add("Staffel_3_Preis_riskcccheck__c".toLowerCase());
        existFields.add("Staffel_3_Preis_riskinfoboprem__c".toLowerCase());
        existFields.add("Staffel_3_Preis_riskinfoboprof__c".toLowerCase());
        existFields.add("Staffel_3_TRX_claimsdunning__c".toLowerCase());
        existFields.add("Staffel_3_TRX_claimspostcolor__c".toLowerCase());
        existFields.add("Staffel_3_TRX_claimspostsw__c".toLowerCase());
        existFields.add("Staffel_3_TRX_claimstransfer__c".toLowerCase());
        existFields.add("Staffel_3_TRX_glauza__c".toLowerCase());
        existFields.add("Staffel_3_TRX_glmaza__c".toLowerCase());
        existFields.add("Staffel_3_TRX_invoiceglobal__c".toLowerCase());
        existFields.add("Staffel_3_TRX_invoicepostcolor__c".toLowerCase());
        existFields.add("Staffel_3_TRX_invoicepostsw__c".toLowerCase());
        existFields.add("Staffel_3_TRX_micropaytrx__c".toLowerCase());
        existFields.add("Staffel_3_TRX_riskadbasic__c".toLowerCase());
        existFields.add("Staffel_3_TRX_riskadperson__c".toLowerCase());
        existFields.add("Staffel_3_TRX_riskbacheck__c".toLowerCase());
        existFields.add("Staffel_3_TRX_riskcccheck__c".toLowerCase());
        existFields.add("Staffel_3_TRX_riskinfoboprem__c".toLowerCase());
        existFields.add("Staffel_3_TRX_riskinfoboprof__c".toLowerCase());
        existFields.add("Staffel_4_Preis_claimsdunning__c".toLowerCase());
        existFields.add("Staffel_4_Preis_claimspostcol__c".toLowerCase());
        existFields.add("Staffel_4_Preis_claimspostsw__c".toLowerCase());
        existFields.add("Staffel_4_Preis_claimstransfer__c".toLowerCase());
        existFields.add("Staffel_4_Preis_glauza__c".toLowerCase());
        existFields.add("Staffel_4_Preis_glmaza__c".toLowerCase());
        existFields.add("Staffel_4_Preis_invoiceglobal__c".toLowerCase());
        existFields.add("Staffel_4_Preis_invoicepostcol__c".toLowerCase());
        existFields.add("Staffel_4_Preis_invoicepostsw__c".toLowerCase());
        existFields.add("Staffel_4_Preis_micropaytrx__c".toLowerCase());
        existFields.add("Staffel_4_Preis_riskadbasic__c".toLowerCase());
        existFields.add("Staffel_4_Preis_riskadperson__c".toLowerCase());
        existFields.add("Staffel_4_Preis_riskbacheck__c".toLowerCase());
        existFields.add("Staffel_4_Preis_riskcccheck__c".toLowerCase());
        existFields.add("Staffel_4_Preis_riskinfoboprem__c".toLowerCase());
        existFields.add("Staffel_4_Preis_riskinfoboprof__c".toLowerCase());
        existFields.add("Staffel_4_TRX_claimsdunning__c".toLowerCase());
        existFields.add("Staffel_4_TRX_claimspostcolor__c".toLowerCase());
        existFields.add("Staffel_4_TRX_claimspostsw__c".toLowerCase());
        existFields.add("Staffel_4_TRX_claimstransfer__c".toLowerCase());
        existFields.add("Staffel_4_TRX_glauza__c".toLowerCase());
        existFields.add("Staffel_4_TRX_glmaza__c".toLowerCase());
        existFields.add("Staffel_4_TRX_invoiceglobal__c".toLowerCase());
        existFields.add("Staffel_4_TRX_invoicepostcolor__c".toLowerCase());
        existFields.add("Staffel_4_TRX_invoicepostsw__c".toLowerCase());
        existFields.add("Staffel_4_TRX_micropaytrx__c".toLowerCase());
        existFields.add("Staffel_4_TRX_riskadbasic__c".toLowerCase());
        existFields.add("Staffel_4_TRX_riskadperson__c".toLowerCase());
        existFields.add("Staffel_4_TRX_riskbacheck__c".toLowerCase());
        existFields.add("Staffel_4_TRX_riskcccheck__c".toLowerCase());
        existFields.add("Staffel_4_TRX_riskinfoboprem__c".toLowerCase());
        existFields.add("Staffel_4_TRX_riskinfoboprof__c".toLowerCase());
        existFields.add("Staffel_5_Preis_claimsdunning__c".toLowerCase());
        existFields.add("Staffel_5_Preis_claimspostcol__c".toLowerCase());
        existFields.add("Staffel_5_Preis_claimspostsw__c".toLowerCase());
        existFields.add("Staffel_5_Preis_claimstransfer__c".toLowerCase());
        existFields.add("Staffel_5_Preis_glauza__c".toLowerCase());
        existFields.add("Staffel_5_Preis_glmaza__c".toLowerCase());
        existFields.add("Staffel_5_Preis_invoiceglobal__c".toLowerCase());
        existFields.add("Staffel_5_Preis_invoicepostcol__c".toLowerCase());
        existFields.add("Staffel_5_Preis_invoicepostsw__c".toLowerCase());
        existFields.add("Staffel_5_Preis_micropaytrx__c".toLowerCase());
        existFields.add("Staffel_5_Preis_riskadbasic__c".toLowerCase());
        existFields.add("Staffel_5_Preis_riskadperson__c".toLowerCase());
        existFields.add("Staffel_5_Preis_riskbacheck__c".toLowerCase());
        existFields.add("Staffel_5_Preis_riskcccheck__c".toLowerCase());
        existFields.add("Staffel_5_Preis_riskinfoboprem__c".toLowerCase());
        existFields.add("Staffel_5_Preis_riskinfoboprof__c".toLowerCase());
        existFields.add("Staffel_5_TRX_claimsdunning__c".toLowerCase());
        existFields.add("Staffel_5_TRX_claimspostcolor__c".toLowerCase());
        existFields.add("Staffel_5_TRX_claimspostsw__c".toLowerCase());
        existFields.add("Staffel_5_TRX_claimstransfer__c".toLowerCase());
        existFields.add("Staffel_5_TRX_glauza__c".toLowerCase());
        existFields.add("Staffel_5_TRX_glmaza__c".toLowerCase());
        existFields.add("Staffel_5_TRX_invoiceglobal__c".toLowerCase());
        existFields.add("Staffel_5_TRX_invoicepostcolor__c".toLowerCase());
        existFields.add("Staffel_5_TRX_invoicepostsw__c".toLowerCase());
        existFields.add("Staffel_5_TRX_micropaytrx__c".toLowerCase());
        existFields.add("Staffel_5_TRX_riskadbasic__c".toLowerCase());
        existFields.add("Staffel_5_TRX_riskadperson__c".toLowerCase());
        existFields.add("Staffel_5_TRX_riskbacheck__c".toLowerCase());
        existFields.add("Staffel_5_TRX_riskcccheck__c".toLowerCase());
        existFields.add("Staffel_5_TRX_riskinfoboprem__c".toLowerCase());
        existFields.add("Staffel_5_TRX_riskinfoboprof__c".toLowerCase());
        existFields.add("Staffel_Preise_claimsdunning__c".toLowerCase());
        existFields.add("Staffel_Preise_claimspostcol__c".toLowerCase());
        existFields.add("Staffel_Preise_claimspostsw__c".toLowerCase());
        existFields.add("Staffel_Preise_claimstransfer__c".toLowerCase());
        existFields.add("Staffel_Preise_Disagio__c".toLowerCase());
        existFields.add("Staffel_Preise_glauza__c".toLowerCase());
        existFields.add("Staffel_Preise_glmaza__c".toLowerCase());
        existFields.add("Staffel_Preise_invoiceglobal__c".toLowerCase());
        existFields.add("Staffel_Preise_invoicepostcol__c".toLowerCase());
        existFields.add("Staffel_Preise_invoicepostsw__c".toLowerCase());
        existFields.add("Staffel_Preise_micropaytrx__c".toLowerCase());
        existFields.add("Staffel_Preise_riskadbasic__c".toLowerCase());
        existFields.add("Staffel_Preise_riskadperson__c".toLowerCase());
        existFields.add("Staffel_Preise_riskbacheck__c".toLowerCase());
        existFields.add("Staffel_Preise_riskcccheck__c".toLowerCase());
        existFields.add("Staffel_Preise_riskinfoboprem__c".toLowerCase());
        existFields.add("Staffel_Preise_riskinfoboprof__c".toLowerCase());
        existFields.add("Staffel_Stufe_1_CLV__c".toLowerCase());
        existFields.add("Staffel_Stufe_1_Disagio__c".toLowerCase());
        existFields.add("Staffel_Stufe_2_CLV__c".toLowerCase());
        existFields.add("Staffel_Stufe_2_Disagio__c".toLowerCase());
        existFields.add("Staffel_Stufe_3_CLV__c".toLowerCase());
        existFields.add("Staffel_Stufe_3_Disagio__c".toLowerCase());
        existFields.add("Staffel_Stufe_4_CLV__c".toLowerCase());
        existFields.add("Staffel_Stufe_4_Disagio__c".toLowerCase());
        existFields.add("Staffel_Stufe_5_CLV__c".toLowerCase());
        existFields.add("Staffel_Stufe_5_Disagio__c".toLowerCase());
        existFields.add("Staffel_Stufe_6_CLV__c".toLowerCase());
        existFields.add("Staffel_Stufe_6_Disagio__c".toLowerCase());
        existFields.add("storno_gebuehr__c".toLowerCase());
        existFields.add("stundensatz__c".toLowerCase());
        existFields.add("teilnahme_3_d_secure__c".toLowerCase());
        existFields.add("Transaktion_1_bis__c".toLowerCase());
        existFields.add("Transaktion_2_ab__c".toLowerCase());
        existFields.add("Transaktion_3_ab__c".toLowerCase());
        existFields.add("Transaktion_4_ab__c".toLowerCase());
        existFields.add("Transaktion_5_ab__c".toLowerCase());
        existFields.add("transaktions_staffel_gruppe__c".toLowerCase());
        existFields.add("velocity_Check__c".toLowerCase());
        existFields.add("versand_per_e_mail_collect__c".toLowerCase());
        existFields.add("versand_per_e_mail_invoicing__c".toLowerCase());
        existFields.add("versand_per_post_collect__c".toLowerCase());
        existFields.add("Versand_per_Post_Collect_Farbe__c".toLowerCase());
        existFields.add("versand_per_post_invoicing__c".toLowerCase());
        existFields.add("Versand_per_Post_Invoicing_Farbe__c".toLowerCase());
        existFields.add("vertragslaufzeit__c".toLowerCase());
        existFields.add("vertragslaufzeit_engl__c".toLowerCase());
        existFields.add("X50_einmaliger_Rabatt__c".toLowerCase());
        existFields.add("zahlungsziel__c".toLowerCase());
        existFields.add("zahlungsziel_engl__c".toLowerCase());
        existFields.add("zeitlich_begrenzter_rabatt__c".toLowerCase());
        existFields.add("Zus_TRX_paydirekt__c".toLowerCase());
        oliDocument
                .getRootElement()
                .getChildren()
                .stream()
                .filter(e->e.getName().equals("fields"))
                .forEach(element -> {
                    String fullName = element.getChildren().stream()
                            .filter(el -> el.getName().equals("fullName"))
                            .findFirst().get().getText();
                    Boolean isFormula = element.getChildren().stream()
                            .anyMatch(el -> el.getName().equals("formula"));
                    if (fullName.contains("__c") && !isFormula) {
                        oliFields.add(fullName);
                    }
                });

        qliDocument
                .getRootElement()
                .getChildren()
                .stream()
                .filter(e->e.getName().equals("fields"))
                .forEach(element -> {
                    String fullName = element.getChildren().stream()
                            .filter(el -> el.getName().equals("fullName"))
                            .findFirst().get().getText();
                    Boolean isFormula = element.getChildren().stream()
                            .anyMatch(el -> el.getName().equals("formula"));
                    if (fullName.contains("__c") && !isFormula) {
                        qliFields.add(fullName);
                    }
                });
        for (String oliField : oliFields) {
            if (qliFields.contains(oliField) && !existFields.contains(oliField.toLowerCase())) {
                String shortField = oliField.replace("__c", "");
                PrintWriter printer = new PrintWriter("PAYONEQuoteSync__QuoteLineSyncField." + shortField + ".md", "UTF-8");
                String data =
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<CustomMetadata xmlns=\"http://soap.sforce.com/2006/04/metadata\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                        "    <label>Sync Field Value</label>\n" +
                        "    <protected>false</protected>\n" +
                        "    <values>\n" +
                        "        <field>PAYONEQuoteSync__OppLineSyncField__c</field>\n" +
                        "        <value xsi:type=\"xsd:string\">" + oliField + "</value>\n" +
                        "    </values>\n" +
                        "</CustomMetadata>\n";
                printer.print(data);
                printer.close();
            }
        }
    }

    private Document getDocument(String pathName) {
        SAXBuilder builder = new  SAXBuilder();
        try {
            return builder.build(new File(pathName));
        } catch (JDOMException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
