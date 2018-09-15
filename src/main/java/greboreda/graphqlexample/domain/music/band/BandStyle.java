package greboreda.graphqlexample.domain.music.band;

import greboreda.graphqlexample.domain.music.genre.MusicGenre;
import greboreda.graphqlexample.domain.music.genre.MusicGenreTag;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BandStyle {

	private final Map<MusicGenre, Set<MusicGenreTag>> style;

	private BandStyle() {
		this.style = new HashMap<>();
	}

	public static BandStyleBuilder create() {
		return new BandStyleBuilder();
	}

	public boolean plays(MusicGenre genre) {
		return style.containsKey(genre);
	}

	public Set<MusicGenreTag> getTagsFor(MusicGenre musicGenre) {
		return style.getOrDefault(musicGenre, Collections.emptySet());
	}

	public Map<MusicGenre,Set<MusicGenreTag>> toMap() {
		return Collections.unmodifiableMap(style);
	}

	public static class BandStyleBuilder {

		private final BandStyle bandStyle;
		private MusicGenre current;

		private final GenreAppender genreAppender = new GenreAppender() {
			@Override
			public TagAppender withGenre(MusicGenre genre) {
				bandStyle.style.remove(genre);
				bandStyle.style.put(genre, new HashSet<>());
				current = genre;
				return tagAppender;
			}
		};

		private final TagAppender tagAppender = new TagAppender() {

			@Override
			public TagAppender withTag(MusicGenreTag tag) {
				bandStyle.style.get(current).add(tag);
				return this;
			}

			@Override
			public GenreAppender and() {
				return genreAppender;
			}

			@Override
			public BandStyle build() {
				return bandStyle;
			}
		};

		private BandStyleBuilder() {
			this.bandStyle = new BandStyle();
		}


		public interface TagAppender {
			TagAppender withTag(MusicGenreTag tag);
			GenreAppender and();
			BandStyle build();
		}

		public interface GenreAppender {
			TagAppender withGenre(MusicGenre musicGenre);
		}

		public TagAppender withGenre(MusicGenre genre) {
			return genreAppender.withGenre(genre);
		}

	}
}
