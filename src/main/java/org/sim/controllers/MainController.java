package org.sim.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rometools.rome.feed.atom.Category;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.atom.Person;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Image;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.synd.SyndPerson;

@CrossOrigin("*")
@RestController
public class MainController {
	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

	@GetMapping(path = "/rss")
	public Channel rss() throws ParseException {
		Channel channel = new Channel();
		channel.setFeedType("rss_2.0");
		channel.setTitle("Exemple de RSS Feed 2.0");
		channel.setDescription("RSS Feed qui affiche quelques un de nos projets sur Github");
		channel.setLink("https://github.com/Youssef-Erradi/rss-atom-example");

		Image image = new Image();
		image.setUrl("https://fpt.usmba.ac.ma/new/wp-content/uploads/2021/12/657657.png");
		image.setTitle("Faculté Polydisciplinaire de Taza");
		image.setHeight(32);
		image.setWidth(32);
		channel.setImage(image);

		Date date = new Date();
		channel.setPubDate(date);

		dateFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT+0"));

		List<Item> items = new ArrayList<>();

		Item item = new Item();
		item.setAuthor("Erradi Youssef");
		item.setLink("https://github.com/Youssef-Erradi/js-mini-projet");
		item.setTitle("Mini projet Javascript");

		com.rometools.rome.feed.rss.Category category = new com.rometools.rome.feed.rss.Category();
		category.setValue("Front-end");
		item.setCategories(Collections.singletonList(category));

		Description description = new Description();
		description.setValue("Un mini projet avec Javascript qui utilise localstorage");
		item.setDescription(description);
		item.setPubDate(dateFormat.parse("31/07/2021 19:20"));

		items.add(item);
		item = new Item();

		item.setAuthor("Mohamed Neddam");
		item.setLink("https://github.com/mohamedNeddam/typingSpeed");
		item.setTitle("Typing speed avec Javascript");

		item.setCategories(Collections.singletonList(category));

		description = new Description();
		description.setValue("Un projet Javascript qui calcul la vitesse d'écriture");
		item.setDescription(description);
		item.setPubDate(dateFormat.parse("06/02/2021 22:54"));

		items.add(item);

		channel.setItems(items);
		return channel;
	}

	@GetMapping(path = "/atom")
	public Feed atom() throws ParseException {
		Feed feed = new Feed();
		feed.setFeedType("atom_1.0");
		feed.setTitle("Exemple de Atom Feed 1.0");
		feed.setId("https://github.com/Youssef-Erradi/rss-atom-example");

		Content subtitle = new Content();
		subtitle.setType("text/plain");
		subtitle.setValue("Atom Feed qui affiche quelques un de nos projets sur Github");
		feed.setSubtitle(subtitle);
		feed.setUpdated(new Date());

		List<Entry> entries = new ArrayList<>();

		Entry entry = new Entry();
		Link link = new Link();
		SyndPerson author = new Person();
		Category category = new Category();
		Content summary = new Content();

		link.setHref("https://github.com/mohamedNeddam/typingSpeed/");
		entry.setAlternateLinks(Collections.singletonList(link));
		author.setName("Mohamed Neddam");
		entry.setAuthors(Collections.singletonList(author));
		entry.setCreated(dateFormat.parse("06/02/2021 22:54"));
		entry.setId("58a116da3e144ac9ad27d7be4f1078ca");
		entry.setTitle("Typing speed avec Javascript");

		category.setTerm("Front-end");
		entry.setCategories(Collections.singletonList(category));

		summary.setValue("Un projet Javascript qui calcul la vitesse d'écriture");
		entry.setSummary(summary);

		entries.add(entry);
		entry = new Entry();
		link = new Link();
		author = new Person();
		category = new Category();
		summary = new Content();

		link.setHref("https://github.com/Youssef-Erradi/js-mini-projet");
		entry.setAlternateLinks(Collections.singletonList(link));
		author.setName("Youssef Erradi");
		entry.setAuthors(Collections.singletonList(author));
		entry.setCreated(dateFormat.parse("31/07/2021 19:20"));
		entry.setId("66963ea169234b7e8daf8a435618ba54");
		entry.setTitle("Mini projet Javascript");

		category.setTerm("Front-end");
		entry.setCategories(Collections.singletonList(category));

		summary.setValue("Un mini projet avec Javascript qui utilise localstorage");
		entry.setSummary(summary);

		entries.add(entry);

		feed.setEntries(entries);
		return feed;
	}
}
