package stores;

import types.NotificationDetailsStoreType;
import utils.CustomConsole;
import utils.JSONHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.apache.kafka.streams.state.Stores;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
@RequiredArgsConstructor
public class NotificationMasterStore {
    public static final String NOTIFICATION_MASTER_TOPIC = "NOTIFICATION_MASTER_TOPIC";
    public static final String NOTIFICATION_MASTER_INTERNAL = "NOTIFICATION_MASTER_INTERNAL";
    public final String STORE = "NOTIFICATION_MASTER_STORE";
    private final CustomConsole customConsole = new CustomConsole();
    private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;
    private final JSONHandler jsonHandler;

    @PostConstruct
    public void init() {
        try {
            KStream<String, String> Notification_Stream = streamsBuilderFactoryBean.getObject().stream(NOTIFICATION_MASTER_TOPIC, Consumed.with(Serdes.String(), Serdes.String()));
            Notification_Stream.mapValues(value -> jsonHandler.parse(value, NotificationDetailsStoreType.class))
                    .to(NOTIFICATION_MASTER_INTERNAL,
                            Produced.with(Serdes.String(), new JsonSerde<>(NotificationDetailsStoreType.class))
                    );
            streamsBuilderFactoryBean.getObject().globalTable(
                    NOTIFICATION_MASTER_INTERNAL,
                    Consumed.with(Serdes.String(), new JsonSerde<>(NotificationDetailsStoreType.class)),
                    Materialized.<String, NotificationDetailsStoreType>as(Stores.persistentKeyValueStore(STORE))
                            .withKeySerde(Serdes.String())
                            .withValueSerde(new JsonSerde<>(NotificationDetailsStoreType.class))
            );
        } catch (Exception e) {
            customConsole.error(String.format("Error establishing %s", STORE), e);
        }
    }


    private ReadOnlyKeyValueStore<String, NotificationDetailsStoreType> getStore() {
        return Objects.requireNonNull(streamsBuilderFactoryBean.getKafkaStreams())
                .store(StoreQueryParameters.fromNameAndType(
                        STORE,
                        QueryableStoreTypes.keyValueStore()
                ));

    }

    public NotificationDetailsStoreType getData(String key) {
        ReadOnlyKeyValueStore<String, NotificationDetailsStoreType> store = getStore();
        return store.get(key);
    }
}
