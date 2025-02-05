# ACTRA

## ACTRA CSV

Account Transactions CSV digester

Java package for parsing exported CSV data of bank account transactions.

### Usage

Import and use as Java package starting with the `CsvDigester.class`.

Expected input format: CSV with bank account transactions (as InputStream)

Output format: `Transaction.class` with date, amount, sender, recipient and message.

> Keyword `SELF` is used to indicate the account holder as Sender or Recipient.
> 
> Keyword `UNKNOWN` is used to indicate unknown Sender or Recipient.


## Supported Providers

Currently, CSV exports from the following providers are supported:

* Provider 01 - S-Pankki
* Provider 02 - Nordea
* TODO: More providers

## Changelog

**Version 1.0.0**
First version with intended functionality.
