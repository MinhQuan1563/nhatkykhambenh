const ctx = document.getElementById('bloodSugarChart').getContext('2d');
new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['', '', ''],
        datasets: [{
            label: 'Đường huyết',
            data: [30, 100, 10],
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true,
                max: 125,
                ticks: {
                    stepSize: 25
                }
            }
        },
        plugins: {
            legend: {
                display: false
            },
            tooltip: {
                callbacks: {
                    label: function(context) {
                        return `Đường huyết: ${context.parsed.y}`;
                    }
                }
            }
        }
    }
});