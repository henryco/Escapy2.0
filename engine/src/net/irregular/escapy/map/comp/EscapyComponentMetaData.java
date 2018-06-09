package net.irregular.escapy.map.comp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public final class EscapyComponentMetaData {
	private final String name;
	private final Class<?> type;
	private final Map.Entry<String, Class<?>>[] arguments;
}
