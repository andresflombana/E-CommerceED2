</div>
    
    <footer class="mt-5 py-4 text-center">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <p class="mb-0">
                        Sistema de Ventas &copy; <%= java.time.Year.now().getValue() %> - Universidad Mariana
                    </p>
                </div>
            </div>
        </div>
    </footer>
    
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    
    <script>
        // Script para hacer funcionar los dropdowns
        document.addEventListener('DOMContentLoaded', function() {
            // Activar los dropdowns de Bootstrap
            var dropdownElementList = [].slice.call(document.querySelectorAll('.dropdown-toggle'));
            dropdownElementList.map(function (dropdownToggleEl) {
                return new bootstrap.Dropdown(dropdownToggleEl);
            });
            
            // Auto-ocultar alertas después de 5 segundos
            setTimeout(function() {
                var alertList = document.querySelectorAll('.alert-auto-close');
                alertList.forEach(function(alert) {
                    if(alert) {
                        var bsAlert = new bootstrap.Alert(alert);
                        setTimeout(function() {
                            bsAlert.close();
                        }, 5000);
                    }
                });
            }, 500);
        });
    </script>
</body>
</html>