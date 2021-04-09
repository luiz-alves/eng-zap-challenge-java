package br.com.olx.challenge.common.portal;

import br.com.olx.challenge.common.config.BoundingBox;
import br.com.olx.challenge.common.config.VivaRealParameter;
import br.com.olx.challenge.common.config.ZapParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PortalSupplierTest {

    @InjectMocks
    private PortalSupplier portalSupplier;

    @Mock
    private Map<String, AbstractPortal> portals;

    @Autowired
    private BoundingBox boundingBox;

    @Autowired
    private ZapParameter zapParameter;

    @Autowired
    private VivaRealParameter vivaRealParameter;

    @BeforeEach
    void before() {
        when(portals.entrySet()).thenReturn(Map.of("zap", new ZapPortal(zapParameter,boundingBox), "vivareal", new VivaRealPortal(vivaRealParameter, boundingBox)).entrySet());
    }

    @Test
    void should_supply_portal_returns_portal() {
        assertDoesNotThrow(() -> {
            portalSupplier.supplyPortal("zap");
        });

        assertTrue(portalSupplier.supplyPortal("zap") instanceof ZapPortal);
    }

    @Test
    void should_supply_portal_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            portalSupplier.supplyPortal("sadada");
        });
    }


}
