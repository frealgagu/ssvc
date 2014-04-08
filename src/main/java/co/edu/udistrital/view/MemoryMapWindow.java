package co.edu.udistrital.view;

import co.edu.udistrital.exception.PLCCommunicationException;
import co.edu.udistrital.service.ApplicationServices;
import co.edu.udistrital.service.PLCService;

import com.github.wolfie.refresher.Refresher;
import com.github.wolfie.refresher.Refresher.RefreshListener;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Table;

public class MemoryMapWindow extends CustomComponent implements RefreshListener {

	@AutoGenerated
    private AbsoluteLayout mainLayout;

	@AutoGenerated
    private NativeButton btnConfiguration;

	@AutoGenerated
    private NativeButton btnLastRecords;

	@AutoGenerated
    private NativeButton btnCurrentStatus;

	@AutoGenerated
    private Refresher refresher;

	@AutoGenerated
    private Table tblData;

	private static final long serialVersionUID = 5844689278778855445L;

	private final InitApplication initApplication;		
	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */	
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public MemoryMapWindow(InitApplication initApplication) {
		this.initApplication = initApplication;
		buildMainLayout();
		setCompositionRoot(mainLayout);
		initialize();
	}
	
	private void initialize() {
		btnCurrentStatus.addListener(new Button.ClickListener() {

            private static final long serialVersionUID = -6682369249694261829L;

			@Override
			public void buttonClick(ClickEvent event) {
				initApplication.showCurrentStatusWindow();
			}
		});
		btnConfiguration.addListener(new Button.ClickListener() {

            private static final long serialVersionUID = 8947046786639007611L;

			@Override
			public void buttonClick(ClickEvent event) {
				initApplication.showConfigurationWindow();
			}
		});
		btnLastRecords.addListener(new Button.ClickListener() {

            private static final long serialVersionUID = -6396362885740352075L;

			@Override
			public void buttonClick(ClickEvent event) {
				initApplication.showLastRecordsWindow();
			}
		});

        tblData.addContainerProperty(0, String.class,  null);
        tblData.addContainerProperty(1, String.class,  null);
        tblData.addContainerProperty(2, String.class,  null);
        tblData.addContainerProperty(3, String.class,  null);
        tblData.addContainerProperty(4, String.class,  null);
        tblData.addContainerProperty(5, String.class,  null);
        tblData.addContainerProperty(6, String.class,  null);
        tblData.addContainerProperty(7, String.class,  null);
        tblData.addContainerProperty(8, String.class,  null);
        tblData.addContainerProperty(9, String.class,  null);
        tblData.addContainerProperty("*", String.class,  null);

        tblData.setStyleName("plc");
        tblData.setCellStyleGenerator(new Table.CellStyleGenerator() {
			private static final long serialVersionUID = -926674153784256144L;

        	public String getStyle(Object itemId, Object propertyId) {
        		if (propertyId == null) {
      				return null;
        		} else {
        			Item item = tblData.getItem(itemId);
        			if (item.getItemProperty(propertyId).getValue().equals("OO")) {
        				return "green";
        			} else if (item.getItemProperty(propertyId).getValue().equals("---")) {
        				return "red";
        			} else {
        				return null;
        			}            			
        		}
        	}
        });
        
        tblData.addListener(new ItemClickEvent.ItemClickListener() {

			private static final long serialVersionUID = 2327555870362165748L;

			public void itemClick(ItemClickEvent event) {
				if(event.isDoubleClick()) {
					Object value = event.getItem().getItemProperty(event.getPropertyId()).getValue();
			        PLCService plcService = ApplicationServices.getPLCService();					
					try {
						if(value.equals("OO")) {
							int row = (Integer) event.getItemId();
							int column = (Integer) event.getPropertyId();								
							plcService.writeCoil((row * 10) + column, false, InitApplication.UNIT_ID);
							update();
						} else if(value.equals("---")) {
							int row = (Integer) event.getItemId();
							int column = (Integer) event.getPropertyId();
							plcService.writeCoil((row * 10) + column, true, InitApplication.UNIT_ID);
							update();
						}
					} catch (PLCCommunicationException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
        
        refresher.setRefreshInterval(1000);
		refresher.addListener(this);        
	}

	private void update() {
        PLCService plcService = ApplicationServices.getPLCService();
        
        try {
        
            String[][] tableData = new String[800][11];
            
            int maxRows = 90;
            
            boolean[] data = plcService.readCoils(0, maxRows * 10, InitApplication.UNIT_ID);
            for(int i = 0; i < maxRows; i++) {
                for(int j = 0; j <= 10; j++) {
                    if(j < 10) {
                        tableData[i][j] = data[(i * 10) + j] ? "OO" : "---";
                    } else {
                        tableData[i][j] = "M" + (i * 10);
                    }
                }
            }
            
            tblData.removeAllItems();
            
            for(int i = 0; i < maxRows; i++) {
            	tblData.addItem(tableData[i], i);
            }
		} catch (PLCCommunicationException ex) {
			ex.printStackTrace();
		}
        
    }
	
	public void refresh(Refresher source) {
		if(isVisible()) {
			source.attach();
			update();
		} else {
			source.detach();
		}
	}	

	@AutoGenerated
    private AbsoluteLayout buildMainLayout() {
	    // common part: create layout
	    mainLayout = new AbsoluteLayout();
	    mainLayout.setImmediate(false);
	    mainLayout.setWidth("580px");
	    mainLayout.setHeight("400px");
	    
	    // top-level component properties
	    setWidth("580px");
	    setHeight("400px");
	    
	    // tblData
	    tblData = new Table();
	    tblData.setImmediate(false);
	    tblData.setWidth("100.0%");
	    tblData.setHeight("280px");
	    mainLayout.addComponent(tblData, "top:0.0px;right:0.0%;left:0.0%;");
	    
	    // refresher
	    refresher = new Refresher();
	    refresher.setImmediate(false);
	    refresher.setWidth("-1px");
	    refresher.setHeight("-1px");
	    mainLayout.addComponent(refresher, "top:0.0px;left:0.0px;");
	    
	    // btnCurrentStatus
	    btnCurrentStatus = new NativeButton();
	    btnCurrentStatus.setCaption("Estado actual");
	    btnCurrentStatus.setIcon(new ThemeResource(
	            "img/current-status-icon-72x72.png"));
	    btnCurrentStatus.setImmediate(true);
	    btnCurrentStatus.setWidth("86px");
	    btnCurrentStatus.setHeight("86px");
	    mainLayout.addComponent(btnCurrentStatus, "top:300.0px;left:40.0px;");
	    
	    // btnLastRecords
	    btnLastRecords = new NativeButton();
	    btnLastRecords.setCaption("\u00DAltimos Registros");
	    btnLastRecords.setIcon(new ThemeResource(
	            "img/last-records-icon-72x72.png"));
	    btnLastRecords.setImmediate(true);
	    btnLastRecords.setWidth("86px");
	    btnLastRecords.setHeight("86px");
	    mainLayout.addComponent(btnLastRecords, "top:300.0px;left:320.0px;");
	    
	    // btnConfiguration
	    btnConfiguration = new NativeButton();
	    btnConfiguration.setCaption("Configuraci\u00F3n");
	    btnConfiguration.setIcon(new ThemeResource(
	            "img/settings-icon-72x72.png"));
	    btnConfiguration.setImmediate(true);
	    btnConfiguration.setWidth("86px");
	    btnConfiguration.setHeight("86px");
	    mainLayout.addComponent(btnConfiguration, "top:300.0px;right:320.0px;");
	    
	    return mainLayout;
    }
}