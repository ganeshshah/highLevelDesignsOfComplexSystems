package interviewpreparation.uniqueidgenerator;


import java.time.Instant;

public class UniqueIdGenertorBitManipulation {
    private static final long EPOCH_OFFSET = 1586054400000L; // Start time: April 5, 2020 (in milliseconds)

    //
    long epochOffset = Instant.parse("2024-01-01T00:00:00Z").toEpochMilli();
    private static final int DATACENTER_ID_BITS = 5;
    private static final int NODE_ID_BITS = 5;
    private static final int SEQUENCE_BITS = 12;

    private static final long MAX_DATACENTER_ID = (1L << DATACENTER_ID_BITS) - 1;
    private static final long MAX_NODE_ID = (1L << NODE_ID_BITS) - 1;
    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

    private long lastTimestamp = -1L;
    private long sequence = 0L;

    private final long datacenterId;
    private final long nodeId;

    public UniqueIdGenertorBitManipulation(long datacenterId, long nodeId) {
        if (datacenterId < 0 || datacenterId > MAX_DATACENTER_ID) {
            throw new IllegalArgumentException("Datacenter ID must be between 0 and " + MAX_DATACENTER_ID);
        }
        if (nodeId < 0 || nodeId > MAX_NODE_ID) {
            throw new IllegalArgumentException("Node ID must be between 0 and " + MAX_NODE_ID);
        }
        this.datacenterId = datacenterId;
        this.nodeId = nodeId;
    }

    public synchronized long generateUniqueId() {
        long currentTimestamp = System.currentTimeMillis();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards, refusing to generate ID for " + (lastTimestamp - currentTimestamp) + " milliseconds");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) % (MAX_SEQUENCE + 1);
            if (sequence == 0) {
                // Sequence overflow, wait until next millisecond
                currentTimestamp = getNextTimestamp();
            }
        } else {
            sequence = 0L; // Reset sequence
        }

        lastTimestamp = currentTimestamp;

        long timestamp = currentTimestamp - EPOCH_OFFSET;

        return (timestamp << (DATACENTER_ID_BITS + NODE_ID_BITS + SEQUENCE_BITS)) |
                (datacenterId << (NODE_ID_BITS + SEQUENCE_BITS)) |
                (nodeId << SEQUENCE_BITS) |
                sequence;
    }

    private long getNextTimestamp() {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        UniqueIdGenertorBitManipulation idGenerator = new UniqueIdGenertorBitManipulation(1, 1); // Example datacenter ID and node ID
        for(int i=0 ; i<10; i++){
            System.out.println("Generated ID: " + idGenerator.generateUniqueId());
        }
    }
}
