import { Component, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { StatsService } from 'src/app/Services/stats/stats.service';
import { Chart, CategoryScale, LinearScale, BarController, BarElement, Title, Tooltip, Legend, registerables, LineElement, PointElement, LineController, TimeScale } from 'chart.js';
// import LineController from 'chart.js/dist/controllers/controller.line';

// Register necessary Chart.js components
// Chart.register(
//   CategoryScale,
//   LinearScale,     // Register Linear Scale
//   BarController,
//   BarElement,
//   Title,
//   Tooltip,
//   Legend,
//   ...registerables // Register other needed components
// );

Chart.register(
  CategoryScale,
  LinearScale,
  LineController,
  PointElement,
  LineElement,
  TimeScale,
  Title,
  Tooltip,
  Legend
);

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements AfterViewInit {
  stats: any;
  incomes: any[] = [];  // Initialized as empty array
  expenses: any[] = []; // Initialized as empty array
  gridStyle = {
    width: "25%",
    textAlign: 'center'
  };

  @ViewChild('incomeLineChartRef') private incomeLineChartRef!: ElementRef<HTMLCanvasElement>; // Ensure proper ElementRef typing
  @ViewChild('expenseLineChartRef') private expenseLineChartRef!: ElementRef<HTMLCanvasElement>; // Ensure proper ElementRef typing

  constructor(private statsservice: StatsService) {
    this.getStats(); // Fetch stats data
    // this.getcharts();
  }

  ngAfterViewInit() {
    this.getcharts(); // Ensure the charts are created after the view is initialized
  }

  createLineChart() {
    if (this.incomes.length > 0 && this.incomeLineChartRef.nativeElement) { // Check if data is present
      const incomeCtx = this.incomeLineChartRef.nativeElement.getContext('2d');
      if (incomeCtx) {
        new Chart(incomeCtx, {
          type: 'line',
          data: {
            labels: this.incomes.map(income =>income.date),
            datasets: [{
              label: 'Income',
              data: this.incomes.map(income => ({ x: new Date(income.date), y: Number(income.amount)})),
              borderWidth: 1,
              backgroundColor: 'rgb(80,200,120)',
              borderColor: 'rgb(0,100,0)',
            }]
          },
          options: {
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
        });
      } else {
        console.error('Failed to get 2D context for income chart.');
      }
    }

    if (this.expenses.length > 0 && this.expenseLineChartRef.nativeElement) { // Check if data is present
      const expenseCtx = this.expenseLineChartRef.nativeElement.getContext('2d');
      if (expenseCtx) {
        new Chart(expenseCtx, {
          type: 'line',
          data: {
            labels: this.expenses.map(expense => expense.date),
            datasets: [{
              label: 'Expense',
              // data: this.expenses.map(expense => expense.amount),
              data: this.expenses.map(expense => ({ x: new Date(expense.date), y: Number(expense.amount) })),
              borderWidth: 1,
              backgroundColor: 'rgb(255,0,0)',
              borderColor: 'rgb(155,0,0)',
            }]
          },
          options: {
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
        });
      } else {
        console.error('Failed to get 2D context for expense chart.');
      }
    }
  }

  getStats() {
    this.statsservice.getstats().subscribe(res => {
      this.stats = res;
    }, error => {
      console.error('Error fetching stats:', error);
    });
  }

  getcharts() {
    this.statsservice.getcharts().subscribe(res => {
      if (res.expenseList && res.incomeList) {
        this.incomes = res.incomeList;
        this.expenses = res.expenseList;
        console.log('Chart Data:', res);
        this.createLineChart(); // Create the chart after data is fetched and DOM is initialized
      } else {
        console.warn('No income or expense data received.');
      }
    }, error => {
      console.error('Error fetching charts data:', error);
    });
  }
}
