/**
 * 
 */
// Registration Form Validation
document.getElementById('registrationForm').addEventListener('submit', function (e) {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    if (password !== confirmPassword) {
        e.preventDefault();
        alert('Passwords do not match!');
    }
});

// Account Form Validation
document.getElementById('accountForm').addEventListener('submit', function (e) {
    const balance = parseFloat(document.getElementById('balance').value);
    const accountType = document.getElementById('accountType').value;

    if (!accountType) {
        e.preventDefault();
        alert('Please select an account type.');
    }

    if (balance < 0) {
        e.preventDefault();
        alert('Initial balance cannot be negative.');
    }
});
