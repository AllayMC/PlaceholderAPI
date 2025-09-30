package org.allaymc.papi;

import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.function.Function;

/**
 * @author daoge_cmd
 */
public class CharsReplacer {

    private static final char HEAD = '{';
    private static final char TAIL = '}';

    /**
     * Replace the placeholders in a given text.
     *
     * @param text   the text to be processed
     * @param player the player used to process the text, can be {@code null}
     * @param lookup the lookup used to find processors for placeholders
     *
     * @return the processed text
     */
    public static String apply(String text, EntityPlayer player, Function<String, PlaceholderProcessor> lookup) {
        final char[] chars = text.toCharArray();
        final StringBuilder builder = new StringBuilder(text.length());

        final StringBuilder identifier = new StringBuilder();
        final StringBuilder parameters = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            final char l = chars[i];

            if (l != HEAD || i + 1 >= chars.length) {
                builder.append(l);
                continue;
            }

            boolean identified = false;
            boolean invalid = true;
            boolean hadSpace = false;

            while (++i < chars.length) {
                final char p = chars[i];

                if (p == ' ' && !identified) {
                    hadSpace = true;
                    break;
                }
                if (p == TAIL) {
                    invalid = false;
                    break;
                }

                if (p == '|' && !identified) {
                    identified = true;
                    continue;
                }

                if (identified) {
                    parameters.append(p);
                } else {
                    identifier.append(p);
                }
            }

            final String identifierString = identifier.toString();
            final String parametersString = parameters.toString();

            identifier.setLength(0);
            parameters.setLength(0);

            if (invalid) {
                builder.append(HEAD).append(identifierString);

                if (identified) {
                    builder.append('_').append(parametersString);
                }

                if (hadSpace) {
                    builder.append(' ');
                }
                continue;
            }

            final PlaceholderProcessor placeholder = lookup.apply(identifierString);
            if (placeholder == null) {
                builder.append(HEAD).append(identifierString);

                if (identified) {
                    builder.append('|');
                }

                builder.append(parametersString).append(TAIL);
                continue;
            }

            final String replacement = placeholder.process(player, parametersString);
            if (replacement == null) {
                builder.append(HEAD).append(identifierString);

                if (identified) {
                    builder.append('|');
                }

                builder.append(parametersString).append(TAIL);
                continue;
            }

            builder.append(replacement);
        }

        return builder.toString();
    }
}
