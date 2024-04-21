package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static hello.advanced.trace.PrefixConstant.*;

@Slf4j
@Component
public class HelloTraceV2 {


    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTImeMs = System.currentTimeMillis();
        printLog(message, traceId);

        return new TraceStatus(traceId, startTImeMs, message);
    }

    //V2에서 추가
    public TraceStatus beginSync(TraceId beforeTraceId, String message) {
        TraceId nextId = beforeTraceId.createNextId();
        Long startTImeMs = System.currentTimeMillis();
        printLog(message, nextId);
        return new TraceStatus(nextId, startTImeMs, message);
    }
    private void printLog(String message, TraceId nextId) {
        log.info("[{}] {}{}", nextId.getId(), addSpace(START_PREFIX, nextId.getLevel()), message);
    }


    public void end(TraceStatus status) {
        complete(status, null);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMS = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMS);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMS, e.toString());
        }
    }

    private Object addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }


    public void exception(TraceStatus status, Exception e) {
        complete(status, e);

    }

}

