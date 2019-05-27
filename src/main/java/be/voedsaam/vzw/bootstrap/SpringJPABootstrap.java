package be.voedsaam.vzw.bootstrap;


import be.voedsaam.vzw.business.*;
import be.voedsaam.vzw.business.impl.Employee;
import be.voedsaam.vzw.business.impl.Partner;
import be.voedsaam.vzw.business.impl.Volunteer;
import be.voedsaam.vzw.enums.*;
import be.voedsaam.vzw.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The Spring JPA bootstrap class is triggered by the ContextRefreshedEvent on startup in Spring.
 * It autowires all the used service interfaces in one Constructor making sure that all services are loaded in context
 * Implementation that is used is based on the spring.profiles.active=springdatajpa property in application.properties
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private DestinationService destinationService;
    private DriveService driveService;
    private ScheduleService scheduleService;
    private ArticleService articleService;
    private ProductService productService;
    private StockService stockService;
    private OrderService orderService;

    @Autowired
    public SpringJPABootstrap(UserService userService, DestinationService destinationService,
                              DriveService driveService, ScheduleService scheduleService,
                              ArticleService articleService,
                              ProductService productService, StockService stockService,
                              OrderService orderService) {
        this.userService = userService;
        this.destinationService = destinationService;
        this.driveService = driveService;
        this.scheduleService = scheduleService;
        this.productService = productService;
        this.stockService = stockService;
        this.articleService = articleService;
        this.orderService = orderService;
    }

    @Override
    // to avoid detached entities use @Transactional !!!!
    @Transactional
    /**
     * method takes the contextRefreshedEvent and loads all test data in the database witch is specified in the application.properties file under recources
     */
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
        loadDestinations();
        loadDrives();
        addDrivesToSchedule();
        loadProducts();
        loadStock();
        loadArticles();
    }


    private void loadStock() {
        Stock voedsaam = new Stock();
        voedsaam.setName("Voedsaam");
        voedsaam.setLocation("Depot Sint niklaas");
        Stock ocmwTemse = new Stock();
        ocmwTemse.setName("Ocmw Temse");
        ocmwTemse.setLocation("depot Sint-Niklaas");
        Partner partner = (Partner) userService.findByEmail("kathy.blomme@gmail.com");
        ocmwTemse.addUser(partner);
        List<Product> productList = (List<Product>) productService.listAll();
        productList.forEach(product ->
        {
            voedsaam.addProduct(product, 100);
            ocmwTemse.addProduct(product, 10);
        });

        stockService.saveOrUpdate(voedsaam);
        stockService.saveOrUpdate(ocmwTemse);

        Order o1 = new Order();
        o1.setPartner(partner);
        o1.addProduct(productList.get(0), 5);
        o1.addProduct(productList.get(1), 5);
        o1.setPickupDateTime(LocalDateTime.of(2019,6,6,9,0));
        o1.setOrderStatus(OrderStatus.NEW);
        o1.setStock(ocmwTemse);


        Order o2 = new Order();
        o2.setPartner(partner);
        o2.addProduct(productList.get(2), 5);
        o2.addProduct(productList.get(3), 5);
        o2.setPickupDateTime(LocalDateTime.of(2019,1,6,9,0));
        o2.setOrderStatus(OrderStatus.COMPLETED);
        o2.setStock(ocmwTemse);

        Order o3 = new Order();
        o3.setPartner(partner);
        o3.addProduct(productList.get(1), 7);
        o3.addProduct(productList.get(3), 7);
        o3.setOrderStatus(OrderStatus.CANCELLED);
        o3.setStock(ocmwTemse);

        Order o4 = new Order();
        o4.setPartner(partner);
        o4.addProduct(productList.get(1), 7);
        o4.addProduct(productList.get(0), 7);
        o4.setPickupDateTime(LocalDateTime.of(2019,9,6,9,0));
        o4.setOrderStatus(OrderStatus.CLOSED);
        o4.setStock(ocmwTemse);

        orderService.saveOrUpdate(o1);
        orderService.saveOrUpdate(o2);
        orderService.saveOrUpdate(o3);
        orderService.saveOrUpdate(o4);

    }

    private void loadProducts() {
        Product fruit;
        Product groente;
        Product fead;
        Product meat;

        fead = new Product();
        fead.setProductType(ProductType.FEAD);
        fead.setName("Magere melk");
        fead.setDescription("verpakt per 6");
        fead.setUnitOfMeasure(6d);
        fead.setSchelfLife(LocalDate.of(2019, 12, 1));
        fead.setDeliveryNr("123456");

        groente = new Product();
        groente.setProductType(ProductType.VEGETABLES);
        groente.setName("bloemkool");
        groente.setDescription("per stuk");
        groente.setUnitOfMeasure(0.5);
        groente.setSchelfLife(LocalDate.of(2019, 6, 1));

        fruit = new Product();
        fruit.setProductType(ProductType.FRUIT);
        fruit.setName("Appelen");
        fruit.setDescription("per 4 verpakt");
        fruit.setUnitOfMeasure(0.9);
        fruit.setSchelfLife(LocalDate.of(2019, 4, 1));
        meat = new Product();
        meat.setProductType(ProductType.MEAT);
        meat.setName("hesp");
        meat.setDescription("100 gr per verpakking");
        meat.setUnitOfMeasure(0.1);
        meat.setSchelfLife(LocalDate.of(2019, 6, 30));

        productService.saveOrUpdate(fead);
        productService.saveOrUpdate(fruit);
        productService.saveOrUpdate(groente);
        productService.saveOrUpdate(meat);
    }

    private void loadArticles() {
        //portal
        File stockFile = new File("C:\\Users\\jeroe\\Documents\\Projects\\vzw-springboot\\src\\main\\resources\\static\\public-pictures\\portal\\stock.jpg");
        // home
        File flyerFile = new File("C:\\Users\\jeroe\\Documents\\Projects\\vzw-springboot\\src\\main\\resources\\static\\public-pictures\\HOME\\Flyer.jpg");
        File crateFile = new File("C:\\Users\\jeroe\\Documents\\Projects\\vzw-springboot\\src\\main\\resources\\static\\public-pictures\\HOME\\crate.jpg");
        // about
        File vrijwilliger1File = new File("C:\\Users\\jeroe\\Documents\\Projects\\vzw-springboot\\src\\main\\resources\\static\\public-pictures\\about\\vrijwilliger1.jpg");
        // news
        File bestelwagenFile = new File("C:\\Users\\jeroe\\Documents\\Projects\\vzw-springboot\\src\\main\\resources\\static\\public-pictures\\news\\bestelwagen.jpg");
        File rotaryFile = new File("C:\\Users\\jeroe\\Documents\\Projects\\vzw-springboot\\src\\main\\resources\\static\\public-pictures\\news\\rotary.jpg");

        Article main1 = new Article();
        main1.setArticleType(ArticleType.GOAL);
        main1.setTitle("Nieuwe Rittenplanner");
        Paragraph paragraph1 = new Paragraph();
        paragraph1.setTitle("Rittenplanner");
        paragraph1.setText("Elk jaar wordt naar schatting een derde van onze wereldwijde voedselproductie verspild, samen goed voor 89 miljoen ton per jaar. Paradoxaal genoeg leven 79 miljoen Europeanen onder de armoedegrens waarvan 1,6 miljoen Belgen of 15% van onze bevolking. Het aantal dat daarvan terugvalt op voedselhulp is 16 miljoen in Europa en 130.030 in België.\n" +
                "\n" +
                "VoedSaam vzw is een sociaal distributieplatform en helpende hand in strijd tegen deze voedseloverschotten en armoede in het Waasland. Concreet willen wij overschotten detecteren, transporteren, opslaan en distribueren aan Wase organisaties (vzw’s en OCMW’s) die o.a. levensmiddelen aanbieden aan mensen in armoede. Met deze bijdrage willen we er mede voor zorgen dat elke Wase burger in armoede, in zijn gemeente beroep kan doen op een uitgebreider en gezonder aanbod voedselhulp. Wij willen ook een faciliterende rol spelen bij het smeden en versterken van partnerschappen zodat we sámen de strijd tegen armoede in het Waasland kunnen intensiveren.\n" +
                "\n" +
                "De coördinatie verloopt vanuit de kantoren van Interwaas (Lamstraat 113, Sint-Niklaas). Het voedseldepot is gevestigd in Kringwinkel Den Azalee (Krijgsbaan 247, Temse). Meer informatie: vind je in de projectnota en flyer onderaan deze pagina of kan je opvragen bij coördinator Els Van de Steene (T 03 780 52 35 – info(at)voedsaam.be) of door ons te volgen op Facebook.");
        main1.addParagraph(paragraph1);
        Picture crate = new Picture();
        crate.setUrl("crate.jpg");
        crate.setAlternateText("crate");
        try {
            crate.setData(Files.readAllBytes(crateFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        main1.setPicture(crate);
        Article main2 = new Article();
        main2.setArticleType(ArticleType.HOME);
        main2.setTitle("Welkom bij Voedsaam");
        Paragraph pa1 = new Paragraph();
        pa1.setTitle("Samen VoedselStromen herbestemmen");
        pa1.setText("Voedsaam vzw is een sociaal distributieplatform dat voedseloverschotten detecteert. + " +
                        "\n" +
                " Transporteert, stockeert en distrubeeert naar Wase OCMW's en vzws die via voedselondersteuning gezinnen met een budget beperkt begeleiden.");
        main2.addParagraph(pa1);
        Picture p1 = new Picture();
        p1.setUrl("Flyer.jpg");
        p1.setAlternateText("Flyer");
        try {
            p1.setData(Files.readAllBytes(flyerFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        main2.setPicture(p1);
        //
        Article vrijwilligers = new Article();
        vrijwilligers.setTitle("Onze Vrijwilligers");
        vrijwilligers.setArticleType(ArticleType.ABOUT);
        Paragraph pa2 = new Paragraph();
        pa2.setTitle("Vele handen maken licht werk");
        pa2.setText("De dagelijkse werking van VoedSaam steunt volledig op vrijwilligers:" +
                " als chauffeur voor de ritten van en naar de verschillende leveranciers en partners, als hulp in het depot," +
                " als administratieve ondersteuning,…… VoedSaam is dan ook voortdurend op zoek naar mensen die bereid zijn een deel van hun vrije tijd te spenderen aan het herbestemmen van voedselrestromen.");
        vrijwilligers.addParagraph(pa2);
        Picture p2 = new Picture();
        p2.setAlternateText("vrijwiller1");
        p2.setUrl("vrijwilliger1.jpg");
        try {
            p2.setData(Files.readAllBytes(vrijwilliger1File.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        vrijwilligers.setPicture(p2);
        //
        Article news = new Article();
        news.setArticleType(ArticleType.NEWS);
        news.setTitle("Rotary Waasland steunt VoedSaam met nieuwe bestelwagen");
        Paragraph pa3 = new Paragraph();
        pa3.setTitle("De vzw Voedsaam heeft een bestelwagen gekregen van Rotary Waasland. Daarmee komt een einde aan de lange zoektocht van de organisatie naar een eigen transportmiddel om de distributie van voedseloverschotten in het Waasland te verbeteren. ");
        pa3.setText("VoedSaam vzw is een sociaal distributieplatform en een helpende hand in de strijd tegen voedseloverschotten en armoede in het Waasland, in de schoot van Interwaas. Het spoort voedseloverschotten op bij verschillende leveranciers, haalt ze bij hen op, stockeert ze in het depot en distribueert ze naar verscheidene partners in het Waasland. Deze partner-vzw’s en -OCMW’s bezorgen deze voeding bij de gezinnen die ze ondersteunen en begeleiden. De coördinatie van VoedSaam verloopt vanuit de kantoren van Interwaas in Sint-Niklaas, het voedseldepot is gevestigd in Kringwinkel Den Azalee aan de Krijgsbaan in Temse.\n" +
                "\n" +
                "“Bedoeling is de voedselondersteuning die gezinnen ontvangen gevarieerder en verser te maken. Hiervoor sluiten we overeenkomsten af met warenhuizen, land- en tuinbouwbedrijven en veilingen. Het ophalen van deze voeding en het leveren in het depot is een intense logistieke oefening, waarbij transport een cruciale factor is”, schetst coördinator Els Van de Steene van VoedSaam.");
        news.addParagraph(pa3);
        pa3.setTitle("Verrassing");
        pa3.setText("Met de schenking van een bestelwagen van de rotaryclubs van het Waasland krijgt VoedSaam nu een eigen transportmiddel, een cruciale stap in de uitbreiding van haar werking. “Het was een grote verrassing. We gingen een presentatie over onze werking geven op een bijeenkomst van de serviceclub, in het vooruitzicht van de sponsoring van thermische boxen. We keerden echter huiswaarts met de boodschap dat bovenop de aankoop van dat professioneel koelmateriaal ook zal gezorgd worden voor een bestelwagen voor de vzw!”\n" +
                "\n" +
                "“Deze camionette is de belangrijkste troef in de uitbreidingsplannen van ons distributieplatform”, aldus Van de Steene. “We moeten over een eigen transportmiddel beschikken om nieuwe leveranciers te bezoeken en dus nieuwe producten te kunnen ophalen. De kosten voor gehuurd transport nemen een grote hap uit het beschikbare budget en daardoor was de actieradius ook beperkt. Die beperking is nu verleden tijd. We zullen onze werking kunnen uitbreiden. Het logo van VoedSaam op de bestelwagen zorgt bovendien voor een grotere herkenning en naambekendheid.”"
        );
        news.addParagraph(pa3);
        Paragraph pa4 = new Paragraph();
        pa4.setTitle("Vrijwilligers welkom");
        pa4.setText("VoedSaam is ook nog op zoek naar vrijwilligers. Een chauffeur voor de bestelwagen, een begeleider van de ritten, hulp in het depot: het zijn taken waarvoor mensen zich kunnen aanmelden. Wie zich geroepen voelt om bij de vrijwilligersploeg van VoedSaam te komen, kan contact opnemen met Els Van de Steene via het nummer 0492/25.06.41 of via e-mail naar els.vandesteene@voedsaam.be. ");
        news.addParagraph(pa4);
        Picture p9 = new Picture();
        p9.setAlternateText("bestelwagen");
        p9.setUrl("bestelwagen.jpg");
        try {
            p9.setData(Files.readAllBytes(bestelwagenFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        news.setPicture(p9);
        Link link = new Link();
        link.setTitle("Lees origineel artikel");
        link.setUrl("https://www.hln.be/regio/temse/rotary-waasland-steunt-voedsaam-met-nieuwe-bestelwagen~a4c8a847/?referer=https%3A%2F%2Fwww.google.com%2F");
        news.addLink(link);

        Article stock = new Article();
        stock.setTitle("Stock Management");
        stock.setArticleType(ArticleType.PORTAL);
        Picture p10 = new Picture();
        p10.setTitle("Stock");
        p10.setAlternateText("stock");
        try {
            p10.setData(Files.readAllBytes(stockFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stock.setPicture(p10);
        Paragraph pa5 = new Paragraph();
        pa5.setTitle("Nieuw stock management is actief voor partners");
        pa5.setText("Vanaf heden kunnen partners online hun stock beheren en bestellingen uitvoeren ");
        stock.addParagraph(pa5);
        articleService.saveOrUpdate(main2);
        articleService.saveOrUpdate(main1);
        articleService.saveOrUpdate(vrijwilligers);
        articleService.saveOrUpdate(news);
        articleService.saveOrUpdate(stock);
    }

    private void addDrivesToSchedule() {
        Schedule schedule = new Schedule();
        schedule.setName("Test Schedule");
        scheduleService.saveOrUpdate(schedule);
        schedule.addUser((Employee) userService.findByEmail("jeroen.herman@voedsaam.be"));
        List<Drive> drives = (List<Drive>) driveService.listAll();
        drives.forEach(schedule::addDrive);
        schedule = new Schedule();
        schedule.setName("Ritten Els");
        scheduleService.saveOrUpdate(schedule);
        schedule.addUser((Employee) userService.findByEmail("els.vandeSteene@voedsaam.be"));
        schedule.addUser((Employee) userService.findByEmail("cindy.depuydt@voedsaam.be"));
    }

    private void loadDestinations() {
        Destination destination1 = new Destination();
        Destination destination2 = new Destination();
        Destination destination3 = new Destination();

        Address a1 = new Address();
        a1.setCity("Sint-Niklaas");
        a1.setPostalCode(9100);
        a1.setStreet("LamStraat");
        a1.setNumber(113);
        destination1.setContactInfo("Els van de Steene:  03 780 52 35");
        destination1.setDestinationName("Vzw Voedsaam");
        destination1.setAddress(a1);

        Address a2 = new Address();
        a2.setCity("Temse");
        a2.setPostalCode(9100);
        a2.setStreet("Krijgsbaan");
        a2.setNumber(247);
        destination2.setContactInfo("Els van de Steene:  03 780 52 35");
        destination2.setDestinationName("Voedsel Depot: Den azalee");
        destination2.setAddress(a2);

        Address a3 = new Address();
        a3.setCity("Sint-Katelijne-Waver");
        a3.setPostalCode(2860);
        a3.setStreet("Mechelsesteenweg");
        a3.setNumber(120);
        destination3.setContactInfo("015 55 11 11");
        destination3.setDestinationName("Belorta");
        destination3.setAddress(a3);
        destination3.addAgreement("afspraak om 6u aan het depot, Krijgsbaan 247 9140 Temse");
        destination3.addAgreement("Ophaal sleutel camionette + hek: in de sleutelkluis op de afgesproken plaats, code krijg je per sms, de avond voor de rit");
        Task task1 = new Task();
        task1.setRole(Role.DRIVER);
        task1.setTitle("Taak 1");
        task1.setDiscription("Ophaal groenten op veiling");
        destination3.addTask(task1);
        Task task2 = new Task();
        task2.setRole(Role.ATTENDEE);
        task2.setTitle("Taak 2");
        task2.setDiscription("Toegangspasjes en ritplanning voor veiling: rood kaft in camionette.");
        destination3.addTask(task2);
        Task task3 = new Task();
        task3.setRole(Role.DEPOTHELP);
        task3.setTitle("Taak 3");
        task3.setDiscription("Klaarzetten voor ophaling, 20 grijze bakken moeten worden voorzien.");
        destination3.addTask(task3);
        destinationService.saveOrUpdate(destination1);
        destinationService.saveOrUpdate(destination2);
        destinationService.saveOrUpdate(destination3);
    }

    private void loadDrives() {
        List<Destination> destinations = (List<Destination>) destinationService.listAll();
        List<Volunteer> users = userService.listAllVolunteers();

        Drive drive1 = new Drive();
        drive1.setDescription("Rit Voedsaam -> Depot");
        drive1.addUser(users.get(0));
        drive1.addUser(users.get(7));
        drive1.addUser(users.get(11));
        drive1.setStartTime(LocalDateTime.of(2019, 1, 01, 9, 30));
        drive1.setEndTime(LocalDateTime.of(2019, 01, 01, 10, 30));
        drive1.addDestination(destinations.get(0));
        drive1.addDestination(destinations.get(1));


        Drive drive2 = new Drive();
        drive2.setDescription("Rit Depot -> Belorta");
        drive2.addUser(users.get(0));
        drive2.addUser(users.get(7));
        drive2.setStartTime(LocalDateTime.of(2019, 02, 01, 9, 30));
        drive2.setEndTime(LocalDateTime.of(2019, 02, 01, 10, 30));
        drive2.addDestination(destinations.get(0));
        drive2.addDestination(destinations.get(2));

        Drive drive3 = new Drive();
        drive3.setDescription("Rit Voedsaam -> Depot -> Belorta");
        drive3.addUser(users.get(0));
        drive3.setStartTime(LocalDateTime.of(2019, 03, 01, 9, 30));
        drive3.setEndTime(LocalDateTime.of(2019, 03, 01, 10, 30));
        drive3.addDestination(destinations.get(0));
        drive3.addDestination(destinations.get(1));
        drive3.addDestination(destinations.get(2));

        driveService.saveOrUpdate(drive1);
        driveService.saveOrUpdate(drive2);
        driveService.saveOrUpdate(drive3);

    }

    private void loadUsers() {
        User admin = new Employee();
        admin.setFullName("admin voedsaam");
        admin.setEmail("admin@voedsaam.be");
        admin.setPassword("Test123");
        admin.setRole(Role.COORDINATOR);
        Authority authority1 = new Authority("ADMIN");
        Authority authority2 = new Authority("USER");
        admin.addAuthority(authority1);
        admin.addAuthority(authority2);
        userService.saveOrUpdate(admin);

        User jeroen;
        User coordinator;
        User logistics;
        User volunteer;
        User partner;
        Address a1;
        a1 = new Address("Sportlaan", 33, 9170, "Sint-Niklaas");
        jeroen = new Employee("jeroen", "herman", "jeroen.herman@voedsaam.be", "037797243", a1, Role.COORDINATOR, Color.RED);
        logistics = new Employee("Cindy", "DePuydt", "cindy.depuydt@voedsaam.be", "03 /780.52.35");
        logistics.setRole(Role.LOGISTICS);
        coordinator = new Employee("Els", "VandeSteene", "els.vandesteene@voedsaam.be", "0492/250641");
        coordinator.setRole(Role.COORDINATOR);
        partner = new Partner("Kathy", "blomme", "kathy.blomme@gmail.com", "unknown");
        partner.setRole(Role.PARTNER);
        volunteer = new Volunteer("leonard", "cleys", "cleysveedee@telenet.be", "unknown");
        volunteer.setRole(Role.VOLUNTEER);
        volunteer.setColor(Color.WHITE);
        jeroen.setPassword("Test123");
        logistics.setPassword("Test123");
        volunteer.setPassword("Test123");
        coordinator.setPassword("Test123");
        partner.setPassword("Test123");
        userService.saveOrUpdate(coordinator);
        userService.saveOrUpdate(jeroen);
        userService.saveOrUpdate(logistics);
        userService.saveOrUpdate(partner);
        userService.saveOrUpdate(volunteer);
        User driver;
        User attendee;
        User depotHelp;
        driver = new Volunteer("Kevin Van Leugenhaege");
        driver.setEmail("kevin@voedsaam.be");
        driver.setPassword("Test123");
        driver.setTel("0472 40 07 94");
        driver.setRole(Role.DRIVER);
        driver.setColor(Color.LIGHTGREEN);
        attendee = new Volunteer("Veerle Van Overtvelt");
        attendee.setTel("0497 16 36 26");
        attendee.setRole(Role.ATTENDEE);
        attendee.setColor(Color.LIGHTYELLOW);


        depotHelp = new Volunteer("Marie-Noelle Delarbre");
        depotHelp.setTel("0474 84 75 91");
        depotHelp.setRole(Role.DEPOTHELP);
        depotHelp.setColor(Color.LIGHTPURPLE);

        Volunteer driver2 = new Volunteer();
        driver2.setFullName("Frans De Block");
        driver2.setEmail("frans@voedsaam.be");
        driver2.setPassword("Test123");
        driver2.setTel("0496 82 23 00");
        driver2.setColor(Color.LIGHTGREY);
        driver.setRole(Role.DRIVER);

        Volunteer attendee2 = new Volunteer();
        attendee2.setFullName("Bart Van Gasse");
        attendee2.setTel("089 64 97 89");
        attendee2.setColor(Color.BLUE);
        attendee2.setRole(Role.ATTENDEE);

        Volunteer driver3 = new Volunteer();
        driver3.setFullName("Leonard Cleys");
        driver3.setEmail("leonard@voedsaam.be");
        driver3.setPassword("Test123");
        driver3.setTel("0472 96 96 19");
        driver3.setColor(Color.LIGHTBLUE);
        driver3.setRole(Role.DRIVER);

        Volunteer attendee3 = new Volunteer();
        attendee3.setFullName("Naufal Ali");
        attendee3.setTel("0465 13 99 11");
        attendee3.setColor(Color.YELLOW);
        attendee3.setRole(Role.ATTENDEE);

        Volunteer driver4 = new Volunteer();
        driver4.setFullName("Kris De Cock");
        driver4.setEmail("kris@voedsaam.be");
        driver4.setPassword("Test123");
        driver4.setTel("0478 02 04 80");
        driver4.setColor(Color.LIGHTORANGE);
        driver4.setRole(Role.DRIVER);

        Volunteer attendee4 = new Volunteer();
        attendee4.setFullName("Stefaan Van Langenhove");
        attendee4.setTel("0495 50 78 93");
        attendee4.setColor(Color.LIGHTBLUE);
        attendee4.setRole(Role.ATTENDEE);

        Volunteer driver5 = new Volunteer();
        driver5.setFullName("Bart Van Gasse");
        driver5.setTel("089 64 97 89");
        driver5.setEmail("bart@voedsaam.be");
        driver5.setPassword("Test123");
        driver5.setColor(Color.BLUE);
        driver5.setRole(Role.DRIVER);

        Volunteer depotHelp2 = new Volunteer();
        depotHelp2.setFullName("Frans De Block");
        depotHelp2.setTel("0496 82 23 00");
        depotHelp2.setColor(Color.LIGHTGREY);
        depotHelp2.setRole(Role.DEPOTHELP);


        Volunteer driver6 = new Volunteer();
        driver6.setFullName("Naufal Ali");
        driver6.setTel("0465 13 99 11");
        driver6.setEmail("naufal@voedsaam.be");
        driver6.setPassword("Test123");
        driver6.setColor(Color.YELLOW);
        driver6.setRole(Role.DRIVER);



        userService.saveOrUpdate(driver);
        userService.saveOrUpdate(driver2);
        userService.saveOrUpdate(driver3);
        userService.saveOrUpdate(driver4);
        userService.saveOrUpdate(driver5);
        userService.saveOrUpdate(driver6);

        userService.saveOrUpdate(attendee);
        userService.saveOrUpdate(attendee2);
        userService.saveOrUpdate(attendee3);
        userService.saveOrUpdate(attendee4);

        userService.saveOrUpdate(depotHelp);
        userService.saveOrUpdate(depotHelp2);




    }
}

