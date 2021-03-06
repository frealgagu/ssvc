package co.edu.udistrital.view.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.edu.udistrital.domain.measure.Interval;
import co.edu.udistrital.service.ApplicationServices;
import co.edu.udistrital.service.MeasureService;

import co.edu.udistrital.view.InitApplication;
import com.github.wolfie.refresher.Refresher;
import com.github.wolfie.refresher.Refresher.RefreshListener;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.util.Util;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Window.Notification;

public class LastRecordsWindow extends CustomComponent implements RefreshListener {

    private static final int BY_SECONDS = 1;
    private static final int BY_MINUTES = 2;
    private static final int BY_HOURS = 3;

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private NativeButton btnMemoryMap;

	@AutoGenerated
	private NativeButton btnCurrentStatus;

	@AutoGenerated
	private NativeButton btnConfiguration;

	@AutoGenerated
	private NativeButton btnControl;

	@AutoGenerated
	private OptionGroup radioFrequency;

	@AutoGenerated
	private Refresher refresher;

	@AutoGenerated
	private Chart chart;

	private static final long serialVersionUID = -2725164558118601200L;

	private InitApplication initApplication;
	
	private DataSeries temperatureSeries;
    private DataSeries pressureSeries;
	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public LastRecordsWindow(InitApplication initApplication) {
		this.initApplication = initApplication;
		buildMainLayout();
		setCompositionRoot(mainLayout);
		initialize();
	}

	private void initialize() {
		btnControl.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = -4021426038318305486L;

			@Override
			public void buttonClick(ClickEvent event) {
				initApplication.showControlWindow();
			}
		});
		btnConfiguration.addListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1763762022344089588L;

			@Override
			public void buttonClick(ClickEvent event) {
				initApplication.showConfigurationWindow();
			}
		});
		btnCurrentStatus.addListener(new Button.ClickListener() {

            private static final long serialVersionUID = -688571120433290392L;

			@Override
			public void buttonClick(ClickEvent event) {
				initApplication.showCurrentStatusWindow();
			}
		});
		btnMemoryMap.addListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1362297965791130357L;

			@Override
			public void buttonClick(ClickEvent event) {
				initApplication.showMemoryMapWindow();
			}
		});
		
		radioFrequency.setStyleName("horizontal");
		radioFrequency.addItem(BY_SECONDS);
		radioFrequency.setItemCaption(BY_SECONDS, "\u00DAltimos\nSegundos");
		radioFrequency.addItem(BY_MINUTES);
		radioFrequency.setItemCaption(BY_MINUTES, "\u00DAltimos\nMinutos");
		radioFrequency.addItem(BY_HOURS);
		radioFrequency.setItemCaption(BY_HOURS, "\u00DAltimas\nHoras");

        radioFrequency.setValue(BY_SECONDS);

		chart.setConfiguration(createInitialConfiguration());

        update();

		refresher.setRefreshInterval(ApplicationServices.getConfigurationService().getWindowRefresherInterval());
		refresher.addListener(this);
	}
	
	public void refresh(Refresher source) {
		if(isVisible()) {
			source.attach();
			update();
		} else {
			source.detach();
		}
	}

    private void update() {
        MeasureService measureService = ApplicationServices.getMeasureService();
        Integer value = (Integer)radioFrequency.getValue();

        List<Interval> temperatureIntervals;
        switch(value) {
            case BY_SECONDS:
                temperatureIntervals = new ArrayList<>(measureService.retrieveLastTemperatureSecondIntervals(5));
                break;
            case BY_MINUTES:
                temperatureIntervals = new ArrayList<>(measureService.retrieveLastTemperatureMinuteIntervals(5));
                break;
            case BY_HOURS:
                temperatureIntervals = new ArrayList<>(measureService.retrieveLastTemperatureHourIntervals(5));
                break;
            default:
                throw new IllegalArgumentException("No frequency selected");
        }
        Collections.reverse(temperatureIntervals);

        if(temperatureIntervals.size() > 0) {
            //Remove items which oversize the intervals
            while(temperatureSeries.size() > temperatureIntervals.size()) {
                temperatureSeries.remove(temperatureSeries.get(0));
            }
            Interval temperatureInterval = temperatureIntervals.get(0);
            for(DataSeriesItem temperatureItem : new ArrayList<>(temperatureSeries.getData())) {
                //Remove each item whose date is earlier to first interval
                if(Util.toHighchartsTS(temperatureInterval.getDateTime().toDate()) > temperatureItem.getX().longValue()) {
                    temperatureSeries.remove(temperatureItem);
                }
            }
        }
        for(int i = 0; i < temperatureIntervals.size(); i++) {
            Interval temperatureInterval = temperatureIntervals.get(i);
            if(temperatureSeries.size() > i) {
                DataSeriesItem temperatureItem = temperatureSeries.get(i);
                boolean update = false;
                if(Util.toHighchartsTS(temperatureInterval.getDateTime().toDate()) != temperatureItem.getX().longValue()) {
                    temperatureItem.setX(temperatureInterval.getDateTime().toDate());
                    update = true;
                }
                if(temperatureInterval.getValue().compareTo((BigDecimal)temperatureItem.getY()) != 0) {
                    temperatureItem.setY(temperatureInterval.getValue());
                    update = true;
                }
                if(update) {
                    temperatureSeries.update(temperatureItem);
                }
            } else {
                DataSeriesItem temperatureItem = new DataSeriesItem(temperatureInterval.getDateTime().toDate(), temperatureInterval.getValue());
                temperatureSeries.add(temperatureItem);
            }
        }

        List<Interval> pressureIntervals;
        switch(value) {
            case BY_SECONDS:
                pressureIntervals = new ArrayList<>(measureService.retrieveLastPressureSecondIntervals(5));
                break;
            case BY_MINUTES:
                pressureIntervals = new ArrayList<>(measureService.retrieveLastPressureMinuteIntervals(5));
                break;
            case BY_HOURS:
                pressureIntervals = new ArrayList<>(measureService.retrieveLastPressureHourIntervals(5));
                break;
            default:
                throw new IllegalArgumentException("No frequency selected");
        }
        Collections.reverse(pressureIntervals);

        if(pressureIntervals.size() > 0) {
            //Remove items which oversize the intervals
            while(pressureSeries.size() > pressureIntervals.size()) {
                pressureSeries.remove(pressureSeries.get(0));
            }
            Interval pressureInterval = pressureIntervals.get(0);
            for(DataSeriesItem pressureItem : new ArrayList<>(pressureSeries.getData())) {
                //Remove each item whose date is earlier to first interval
                if(Util.toHighchartsTS(pressureInterval.getDateTime().toDate()) > pressureItem.getX().longValue()) {
                    pressureSeries.remove(pressureItem);
                }
            }
        }
        for(int i = 0; i < pressureIntervals.size(); i++) {
            Interval pressureInterval = pressureIntervals.get(i);
            if(pressureSeries.size() > i) {
                DataSeriesItem pressureItem = pressureSeries.get(i);
                boolean update = false;
                if(Util.toHighchartsTS(pressureInterval.getDateTime().toDate()) != pressureItem.getX().longValue()) {
                    pressureItem.setX(pressureInterval.getDateTime().toDate());
                    update = true;
                }
                if(pressureInterval.getValue().compareTo((BigDecimal)pressureItem.getY()) != 0) {
                    pressureItem.setY(pressureInterval.getValue());
                    update = true;
                }
                if(update) {
                    pressureSeries.update(pressureItem);
                }
            } else {
                DataSeriesItem pressureItem = new DataSeriesItem(pressureInterval.getDateTime().toDate(), pressureInterval.getValue());
                pressureSeries.add(pressureItem);
            }
        }

        //chart.drawChart();
    }

	private Configuration createInitialConfiguration() {
		Configuration configuration = new Configuration();
        configuration.getChart().setType(ChartType.LINE);
        configuration.getChart().setMarginRight(105);
        configuration.getChart().setMarginBottom(25);

        configuration.getTitle().setText("Gr\u00E1fica de Temperatura y Presi\u00F3n");
        configuration.getSubTitle().setText("Gr\u00E1fica de Temperatura y Presi\u00F3n");

        Axis xAxis = configuration.getxAxis();
        xAxis.setAllowDecimals(false);
        xAxis.setType(AxisType.DATETIME);

        Axis yAxis = configuration.getyAxis();
        yAxis.setMin(0);
        yAxis.setTitle(new Title("Temperatura / Presi\u00F3n"));
        yAxis.getTitle().setVerticalAlign(VerticalAlign.HIGH);

        configuration.getTooltip().setFormatter("'' + this.series.name + ': ' + this.y + (this.series.name == 'Temperatura' ? ' °C (Grados Celcius)' : ' psi (Libras por Pulgada Cuadrada)')");

        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setDataLabels(new Labels(true));
        configuration.setPlotOptions(plotOptions);

        Legend legend = configuration.getLegend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(10);
        legend.setY(100d);
        legend.setBorderWidth(0);
		
		MeasureService measureService = ApplicationServices.getMeasureService();
		List<Interval> pressureIntervals = new ArrayList<Interval>(measureService.retrieveLastPressureSecondIntervals(5));
		Collections.reverse(pressureIntervals);
		List<Interval> temperatureIntervals = new ArrayList<Interval>(measureService.retrieveLastTemperatureSecondIntervals(5));
		Collections.reverse(temperatureIntervals);
		temperatureSeries = new DataSeries();
		temperatureSeries.setName("Temperatura");
        pressureSeries = new DataSeries();
        pressureSeries.setName("Presi\u00F3n");

		for(Interval temperatureinterval : temperatureIntervals) {
			temperatureSeries.add(new DataSeriesItem(temperatureinterval.getDateTime().toDate(), temperatureinterval.getValue()));
		}
        for(Interval pressureInterval : pressureIntervals) {
            pressureSeries.add(new DataSeriesItem(pressureInterval.getDateTime().toDate(), pressureInterval.getValue()));
        }
        configuration.addSeries(temperatureSeries);
        configuration.addSeries(pressureSeries);
        return configuration;
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
		
		// chart
		chart = new Chart();
		chart.setImmediate(false);
		chart.setWidth("100.0%");
		chart.setHeight("260px");
		mainLayout.addComponent(chart, "top:0.0px;right:0.0px;left:0.0px;");
		
		// refresher
		refresher = new Refresher();
		refresher.setImmediate(false);
		refresher.setWidth("-1px");
		refresher.setHeight("-1px");
		mainLayout.addComponent(refresher, "top:0.0px;left:0.0px;");
		
		// radioFrequency
		radioFrequency = new OptionGroup();
		radioFrequency.setImmediate(false);
		radioFrequency.setWidth("540px");
		radioFrequency.setHeight("32px");
		mainLayout.addComponent(radioFrequency, "top:270.0px;left:20.0px;");
		
		// btnControl
		btnControl = new NativeButton();
		btnControl.setCaption("Control PLC");
		btnControl.setIcon(new ThemeResource("img/plc-registers-settings-icon-72x72.png"));
		btnControl.setImmediate(true);
		btnControl.setWidth("86px");
		btnControl.setHeight("86px");
		mainLayout.addComponent(btnControl, "top:300.0px;left:25.0px;");
		
		// btnConfiguration
		btnConfiguration = new NativeButton();
		btnConfiguration.setCaption("Configuraci\u00F3n");
		btnConfiguration.setIcon(new ThemeResource("img/settings-icon-72x72.png"));
		btnConfiguration.setImmediate(true);
		btnConfiguration.setWidth("86px");
		btnConfiguration.setHeight("86px");
		mainLayout.addComponent(btnConfiguration, "top:300.0px;left:136.0px;");
		
		// btnCurrentStatus
		btnCurrentStatus = new NativeButton();
		btnCurrentStatus.setCaption("Estado actual");
		btnCurrentStatus.setIcon(new ThemeResource("img/current-status-icon-72x72.png"));
		btnCurrentStatus.setImmediate(true);
		btnCurrentStatus.setWidth("86px");
		btnCurrentStatus.setHeight("86px");
		mainLayout.addComponent(btnCurrentStatus, "top:300.0px;left:247.0px;");
		
		// btnMemoryMap
		btnMemoryMap = new NativeButton();
		btnMemoryMap.setCaption("Mapa de memoria");
		btnMemoryMap.setIcon(new ThemeResource("img/memory-map-icon-72x72.png"));
		btnMemoryMap.setImmediate(true);
		btnMemoryMap.setWidth("86px");
		btnMemoryMap.setHeight("86px");
		mainLayout.addComponent(btnMemoryMap, "top:300.0px;left:469.0px;");
		
		return mainLayout;
	}
}
